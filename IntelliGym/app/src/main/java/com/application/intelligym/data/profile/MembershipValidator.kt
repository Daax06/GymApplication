package com.application.intelligym.data.profile

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.Date

suspend fun checkMembershipActive(): Boolean {
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return false
    val snapshot = Firebase.firestore.collection("memberships").document(userId).get().await()
    val activatedAt = snapshot.getTimestamp("activatedAt")?.toDate() ?: return false
    val isExpired = Date().time - activatedAt.time > 30L * 24 * 60 * 60 * 1000
    return !isExpired
}
