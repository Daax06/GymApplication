    package com.application.intelligym.data

    data class RegistrationUIState(
        var phoneNumber: String = "",
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var password: String = "",
        var privacyPolicyAccepted: Boolean = false,


        var phoneNumberError: Boolean = false,
        var firstNameError: Boolean = false,
        var lastNameError: Boolean = false,
        var emailError: Boolean = false,
        var passwordError: Boolean = false,
        var privacyPolicyError: Boolean = false


)