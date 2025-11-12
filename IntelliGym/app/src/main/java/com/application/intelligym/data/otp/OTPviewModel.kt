            package com.application.intelligym.data.otp

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class OtpViewModel : ViewModel() {

    private val TAG = "OtpViewModel"

    // Firebase instances
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    // Phone OTP fields
    private var storedVerificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    // --- PHONE VERIFICATION (existing) ---
    fun sendOtp(phoneNumber: String, activity: Activity, onCodeSent: () -> Unit, onFailure: (String) -> Unit) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    Log.d(TAG, "onVerificationCompleted: $credential")
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e(TAG, "onVerificationFailed: ${e.message}")
                    onFailure(e.message ?: "Verification failed")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    Log.d(TAG, "onCodeSent: $verificationId")
                    storedVerificationId = verificationId
                    resendToken = token
                    onCodeSent()
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(otp: String, onResult: (Boolean) -> Unit) {
        val verificationId = storedVerificationId
        if (verificationId != null) {
            val credential = PhoneAuthProvider.getCredential(verificationId, otp)
            signInWithPhoneAuthCredential(credential, onResult)
        } else {
            onResult(false)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, onResult: (Boolean) -> Unit) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential: success")
                    onResult(true)
                } else {
                    Log.w(TAG, "signInWithCredential: failed", task.exception)
                    onResult(false)
                }
            }
    }

    fun sendOtpToEmail(email: String, onSent: () -> Unit, onFailure: (String) -> Unit) {
        val generatedOtp = (100000..999999).random().toString()

        val otpData = hashMapOf(
            "otp" to generatedOtp,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("emailOtps").document(email).set(otpData)
            .addOnSuccessListener {
                Log.d(TAG, "Email OTP sent to Firestore: $generatedOtp")
                // TODO: Trigger your email sending backend here (if you have one)
                onSent()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Failed to store email OTP: ${e.message}")
                onFailure(e.message ?: "Failed to send OTP")
            }
    }

    fun verifyEmailOtp(email: String, inputOtp: String, onResult: (Boolean) -> Unit) {
        db.collection("emailOtps").document(email).get()
            .addOnSuccessListener { document ->
                val storedOtp = document.getString("otp")
                if (storedOtp != null && storedOtp == inputOtp) {
                    Log.d(TAG, "Email OTP verified!")
                    onResult(true)
                } else {
                    Log.d(TAG, "Email OTP incorrect.")
                    onResult(false)
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error verifying email OTP: ${e.message}")
                onResult(false)
            }
    }
}