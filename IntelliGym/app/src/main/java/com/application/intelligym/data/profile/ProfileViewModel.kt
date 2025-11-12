package com.application.intelligym.data.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.application.intelligym.data.profile.User

class ProfileViewModel : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val uid = auth.currentUser?.uid

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        uid?.let { userId ->
            db.collection("users").document(userId)
                .addSnapshotListener { snapshot, e ->
                    if (snapshot != null && snapshot.exists()) {
                        val userData = snapshot.toObject(User::class.java)
                        _user.value = userData
                    }
                }
        }
    }

    fun updateBio(newBio: String) {
        uid?.let { userId ->
            db.collection("users").document(userId)
                .update("bio", newBio)
        }
    }
}
