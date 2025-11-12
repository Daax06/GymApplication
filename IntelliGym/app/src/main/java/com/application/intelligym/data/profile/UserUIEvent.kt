package com.application.intelligym.data.profile

data class User(
    val email: String = "",
    val username: String = "",
    val profilePicUrl: String = "",
    val bio: String = "",
    val membership: Membership = Membership(),
    val workoutHistory: List<Workout> = emptyList()
)

data class Membership(
    val type: String = "",
    val expiryDate: String = ""
)

data class Workout(
    val name: String = "",
    val date: String = ""
)
