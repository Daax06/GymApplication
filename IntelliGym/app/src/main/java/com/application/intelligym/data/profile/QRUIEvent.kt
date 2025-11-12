package com.application.intelligym.data.profile

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun validateTransactionAndUnlock(transactionId: String) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
    val ref = Firebase.firestore.collection("transactions")

    ref.whereEqualTo("transactionId", transactionId)
        .whereEqualTo("userId", userId)
        .get()
        .addOnSuccessListener { result ->
            if (!result.isEmpty) {
                val doc = result.documents.first()
                val timestamp = Timestamp.now()
                Firebase.firestore.collection("memberships").document(userId)
                    .set(
                        mapOf(
                            "isActive" to true,
                            "activatedAt" to timestamp
                        )
                    )
            } else {
                // Show error: invalid transaction
            }
        }
}