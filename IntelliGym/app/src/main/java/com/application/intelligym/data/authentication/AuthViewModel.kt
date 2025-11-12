package com.application.intelligym.data.authentication

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

// Data classes for user and auth state management
data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val phoneNumber: String = "",
    val isEmailVerified: Boolean = false,
    val isPhoneVerified: Boolean = false
)

sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    // OTP verification variables
    private var verificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    private val phoneAuthCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("AuthViewModel", "Phone verification completed automatically")
            signInWithPhoneCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.e("AuthViewModel", "Phone verification failed", e)
            _authState.value = AuthState.Error("Phone verification failed: ${e.message}")
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d("AuthViewModel", "OTP code sent")
            this@AuthViewModel.verificationId = verificationId
            this@AuthViewModel.resendToken = token
            _authState.value = AuthState.Success("OTP sent successfully")
        }
    }

    init {
        checkAuthState()
    }

    private fun checkAuthState() {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            loadUserData(firebaseUser.uid)
            _isUserLoggedIn.value = true
        } else {
            _isUserLoggedIn.value = false
        }
    }

    fun loginUser(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Please fill in all fields")
            return
        }

        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("Please enter a valid email address")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                result.user?.let { firebaseUser ->
                    loadUserData(firebaseUser.uid)
                    _isUserLoggedIn.value = true
                    _authState.value = AuthState.Success("Login successful")
                } ?: run {
                    _authState.value = AuthState.Error("Login failed")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Login error", e)
                _authState.value = AuthState.Error(getFirebaseErrorMessage(e))
            }
        }
    }

    fun registerUser(email: String, password: String, displayName: String, phoneNumber: String = "") {
        if (email.isBlank() || password.isBlank() || displayName.isBlank()) {
            _authState.value = AuthState.Error("Please fill in all required fields")
            return
        }

        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("Please enter a valid email address")
            return
        }

        if (password.length < 6) {
            _authState.value = AuthState.Error("Password must be at least 6 characters")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                result.user?.let { firebaseUser ->
                    // Update display name
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build()
                    firebaseUser.updateProfile(profileUpdates).await()

                    // Save user data to Firestore
                    val user = User(
                        uid = firebaseUser.uid,
                        email = email,
                        displayName = displayName,
                        phoneNumber = phoneNumber,
                        isEmailVerified = firebaseUser.isEmailVerified
                    )
                    saveUserToFirestore(user)

                    _currentUser.value = user
                    _isUserLoggedIn.value = true
                    _authState.value = AuthState.Success("Registration successful")
                } ?: run {
                    _authState.value = AuthState.Error("Registration failed")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Registration error", e)
                _authState.value = AuthState.Error(getFirebaseErrorMessage(e))
            }
        }
    }

    fun sendOTP(phoneNumber: String, activity: Activity) {
        if (phoneNumber.isBlank()) {
            _authState.value = AuthState.Error("Please enter a phone number")
            return
        }

        _authState.value = AuthState.Loading

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(phoneAuthCallbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOTP(code: String) {
        if (code.isBlank()) {
            _authState.value = AuthState.Error("Please enter the OTP code")
            return
        }

        if (verificationId == null) {
            _authState.value = AuthState.Error("No verification ID found. Please request OTP again")
            return
        }

        _authState.value = AuthState.Loading

        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneCredential(credential)
    }

    private fun signInWithPhoneCredential(credential: PhoneAuthCredential) {
        viewModelScope.launch {
            try {
                val currentFirebaseUser = auth.currentUser
                if (currentFirebaseUser != null) {
                    // Link phone credential to existing account
                    currentFirebaseUser.linkWithCredential(credential).await()
                    updateUserPhoneVerification(currentFirebaseUser.uid, credential.smsCode ?: "")
                } else {
                    // Sign in with phone credential
                    val result = auth.signInWithCredential(credential).await()
                    result.user?.let { firebaseUser ->
                        loadUserData(firebaseUser.uid)
                        _isUserLoggedIn.value = true
                    }
                }
                _authState.value = AuthState.Success("Phone verification successful")
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Phone credential sign-in error", e)
                _authState.value = AuthState.Error(getFirebaseErrorMessage(e))
            }
        }
    }

    fun resendOTP(activity: Activity) {
        _currentUser.value?.phoneNumber?.let { phoneNumber ->
            if (resendToken != null) {
                _authState.value = AuthState.Loading

                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(phoneNumber)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(activity)
                    .setCallbacks(phoneAuthCallbacks)
                    .setForceResendingToken(resendToken!!)
                    .build()

                PhoneAuthProvider.verifyPhoneNumber(options)
            } else {
                sendOTP(phoneNumber, activity)
            }
        } ?: run {
            _authState.value = AuthState.Error("No phone number found")
        }
    }

    fun forgotPassword(email: String) {
        if (email.isBlank()) {
            _authState.value = AuthState.Error("Please enter your email address")
            return
        }

        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("Please enter a valid email address")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                auth.sendPasswordResetEmail(email).await()
                _authState.value = AuthState.Success("Password reset email sent")
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Password reset error", e)
                _authState.value = AuthState.Error(getFirebaseErrorMessage(e))
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                auth.signOut()
                _currentUser.value = null
                _isUserLoggedIn.value = false
                _authState.value = AuthState.Success("Logged out successfully")
                clearAuthState()
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Logout error", e)
                _authState.value = AuthState.Error("Logout failed")
            }
        }
    }

    private fun loadUserData(uid: String) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("users").document(uid).get().await()
                if (document.exists()) {
                    val user = document.toObject(User::class.java)?.copy(uid = uid)
                    _currentUser.value = user
                } else {
                    // Create user document if it doesn't exist
                    val firebaseUser = auth.currentUser
                    firebaseUser?.let {
                        val user = User(
                            uid = it.uid,
                            email = it.email ?: "",
                            displayName = it.displayName ?: "",
                            phoneNumber = it.phoneNumber ?: "",
                            isEmailVerified = it.isEmailVerified
                        )
                        saveUserToFirestore(user)
                        _currentUser.value = user
                    }
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error loading user data", e)
            }
        }
    }

    private fun saveUserToFirestore(user: User) {
        viewModelScope.launch {
            try {
                firestore.collection("users").document(user.uid).set(user).await()
                Log.d("AuthViewModel", "User data saved successfully")
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error saving user data", e)
            }
        }
    }

    private fun updateUserPhoneVerification(uid: String, phoneNumber: String) {
        viewModelScope.launch {
            try {
                firestore.collection("users").document(uid)
                    .update(
                        mapOf(
                            "phoneNumber" to phoneNumber,
                            "isPhoneVerified" to true
                        )
                    ).await()

                _currentUser.value = _currentUser.value?.copy(
                    phoneNumber = phoneNumber,
                    isPhoneVerified = true
                )
                Log.d("AuthViewModel", "Phone verification updated")
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error updating phone verification", e)
            }
        }
    }

    fun clearAuthState() {
        _authState.value = AuthState.Idle
        verificationId = null
        resendToken = null
    }

    @RequiresApi(Build.VERSION_CODES.FROYO)
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun getFirebaseErrorMessage(exception: Exception): String {
        return when (exception) {
            is FirebaseAuthUserCollisionException ->     "An account with this email already exists"
            is FirebaseAuthWeakPasswordException -> "Password is too weak"
            is FirebaseAuthInvalidCredentialsException -> "Invalid email or password"
            is FirebaseAuthInvalidUserException -> "No account found with this email"
            is FirebaseNetworkException -> "Network error. Please check your connection"
            else -> exception.message ?: "An unknown error occurred"
        }
    }
}