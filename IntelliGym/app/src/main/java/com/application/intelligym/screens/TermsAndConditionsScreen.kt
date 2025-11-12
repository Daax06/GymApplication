package com.application.intelligym.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.intelligym.R
import com.application.intelligym.components.HeadingTextComponent
import com.application.intelligym.navigation.IntelliGymAppRouter
import com.application.intelligym.navigation.Screen
import com.application.intelligym.navigation.SystemBackButtonHandler
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun TermsAndConditionsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_header))
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Terms and Conditions of Use\n" +
                        "Effective Date: March 15, 2025\n" +
                        "Last Updated: May 20, 2025\n" +
                        "Welcome to IntelliGym, the official web and mobile application of Dok's Gym, developed and managed by a group of NTC BSIT Students. By downloading, accessing, or using the App, you, the user, agree to be bound by these Terms and Conditions.\n" +
                        "Please read these Terms carefully. If you do not agree with any part of these Terms, you must not use the App.\n" +
                        "\n" +
                        "\n" +
                        "1. Purpose of the Application\n" +
                        "IntelliGym is designed exclusively for registered members of Dok's Gym to:\n" +
                        "Record and monitor attendance\n" +
                        "Validate membership status\n" +
                        "Track real-time entry and exit activity\n" +
                        "This App is intended to streamline gym operations, enhance security, and improve member experience.\n" +
                        "\n" +
                        "2. Eligibility\n" +
                        "To use IntelliGym, you must:\n" +
                        "Be at least 18 years old, or have parental/guardian consent if under 18\n" +
                        "Be a registered and active member of Dok's Gym\n" +
                        "Provide accurate and complete information during registration and app use\n" +
                        "We reserve the right to deny or revoke access if eligibility requirements are not met.\n" +
                        "\n" +
                        "3. User Accounts\n" +
                        "You are responsible for maintaining the confidentiality of your account and login credentials.\n" +
                        "You agree not to share your account with others or use another member’s account.\n" +
                        "You must notify us immediately of any unauthorized access or suspected breach of security.\n" +
                        "We reserve the right to suspend or terminate any account found in violation of these Terms.\n" +
                        "\n" +
                        "4. Privacy and Data Protection\n" +
                        "We comply with Republic Act No. 10173, also known as the Data Privacy Act of 2012. By using this App, you consent to the collection and use of your personal data for gym-related purposes.\n" +
                        "Information We Collect:\n" +
                        "Full name and contact details\n" +
                        "Membership number and attendance records\n" +
                        "Entry and exit logs\n" +
                        "Optional: biometric data or QR code scans\n" +
                        "How We Use It:\n" +
                        "To verify your membership\n" +
                        "To record and track your gym visits\n" +
                        "To ensure safety, security, and facility management\n" +
                        "Your information is securely stored and only accessible to authorized personnel. It will not be sold or shared with third parties unless required by law or with your explicit consent. For further details, refer to our [Privacy Policy].\n" +
                        "\n" +
                        "5. Acceptable Use\n" +
                        "By using IntelliGym, you agree to:\n" +
                        "Use the App only for its intended purpose as a gym attendance and monitoring system\n" +
                        "Provide true and accurate information at all times\n" +
                        "Not use the App to commit fraud, disrupt service, or harm other users\n" +
                        "Not attempt to reverse-engineer, hack, or interfere with App functionality or data\n" +
                        "Violations may result in suspension, termination, or legal action.\n" +
                        "\n" +
                        "6. Suspension or Termination\n" +
                        "Your access to IntelliGym may be suspended or terminated under the following conditions:\n" +
                        "Violation of these Terms and Conditions\n" +
                        "Providing false or misleading information\n" +
                        "Unauthorized use or misuse of App features\n" +
                        "Expiry or cancellation of your gym membership\n" +
                        "We will notify you when appropriate but reserve the right to act immediately in serious cases.\n" +
                        "\n" +
                        "7. Intellectual Property\n" +
                        "All content and components of the IntelliGym App, including but not limited to text, graphics, logos, software, and trademarks, are the property of Dok’s Gym and/or the NTC BSIT Students who developed it.\n" +
                        "You may not reproduce, distribute, modify, or create derivative works without prior written consent.\n" +
                        "\n" +
                        "8. Limitation of Liability\n" +
                        "To the fullest extent allowed by law, Dok’s Gym and the NTC BSIT Students will not be liable for:\n" +
                        "Any indirect, incidental, or consequential damages\n" +
                        "Any loss of data, unauthorized access, or system failure not caused by gross negligence\n" +
                        "Service interruptions due to technical issues, updates, or external factors\n" +
                        "Use of the App is at your own risk.\n" +
                        "\n" +
                        "9. Updates and Modifications\n" +
                        "We may modify these Terms at any time to reflect changes in the App, legal requirements, or business needs. You may be notified of significant changes via the App or registered email.\n" +
                        "Continued use of IntelliGym after updates means you accept the revised Terms at any given moment.\n" +
                        "\n" +
                        "10. Governing Law\n" +
                        "These Terms are governed by and shall be interpreted in accordance with the laws of the Republic of the Philippines. Any disputes arising under these Terms shall be subject to the exclusive jurisdiction of the courts of the Philippines.\n" +
                        "\n" +
                        "11. Contact Information\n" +
                        "If you have questions, concerns, or feedback about the IntelliGym App or these Terms, you may contact:\n" +
                        "Dok’s Gym Support Team\n" +
                        " Email: 422002468@ntc.edu.ph\n" +
                        " Phone: 0961 862 1769\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Privacy Policy\n" +
                        "Effective Date: March 15, 2025\n" +
                        " Last Updated: May 20, 2025\n" +
                        "This Privacy Policy explains how Dok’s Gym, through its official mobile and web application IntelliGym, developed by NTC BSIT Students, collects, uses, stores, and protects your personal information in accordance with Republic Act No. 10173, also known as the Data Privacy Act of 2012 of the Philippines.\n" +
                        "By accessing and using IntelliGym, you consent to the collection and processing of your personal data as described in this Policy.\n" +
                        "\n" +
                        "1. Information We Collect\n" +
                        "We collect the following personal and usage information from registered users of IntelliGym:\n" +
                        "Personal Identifiable Information (PII):\n" +
                        "Full Name\n" +
                        "Contact Information (e.g., mobile number, email address)\n" +
                        "Membership ID or registration number\n" +
                        "Usage and Access Data:\n" +
                        "Attendance logs (check-ins/check-outs)\n" +
                        "Entry and exit timestamps\n" +
                        "Device type, IP address, and location data (if applicable)\n" +
                        "Biometric data or QR code information (if implemented)\n" +
                        "\n" +
                        "2. Purpose of Data Collection\n" +
                        "We collect your data for the following lawful and legitimate purposes:\n" +
                        "To verify your identity and gym membership\n" +
                        "To monitor attendance and gym usage\n" +
                        "To control and secure access to the gym premises\n" +
                        "To improve operational management and user experience\n" +
                        "To communicate important updates related to your membership\n" +
                        "\n" +
                        "3. Legal Basis for Processing\n" +
                        "We process your personal data based on the following lawful criteria under Philippine law:\n" +
                        "Your explicit consent provided during account registration or App use\n" +
                        "Fulfillment of a contractual obligation (e.g., managing your gym membership)\n" +
                        "Compliance with legal obligations\n" +
                        "Legitimate interest of Dok’s Gym to maintain security and monitor facility usage\n" +
                        "\n" +
                        "4. Data Sharing and Disclosure\n" +
                        "We will not sell, rent, or trade your personal information. Your data may only be shared with:\n" +
                        "Authorized Dok’s Gym personnel and administrators\n" +
                        "IT service providers under a data-sharing agreement\n" +
                        "Government authorities, only when required by law or regulation (and only when proper documentation is provided to allow them access to data stored within the system)\n" +
                        "All third parties with access to your data are contractually obligated to uphold confidentiality and comply with the Data Privacy Act.\n" +
                        "\n" +
                        "5. Data Storage and Retention\n" +
                        "Your personal data is securely stored in our system with appropriate safeguards.\n" +
                        "We retain your data only as long as necessary to fulfill the stated purposes or comply with legal requirements.\n" +
                        "Inactive or expired accounts will be deleted or anonymized after a defined period, unless legally required to be retained.\n" +
                        "\n" +
                        "6. Your Data Privacy Rights\n" +
                        "Under the Data Privacy Act, you have the following rights:\n" +
                        "Right to be informed – Know what data is being collected and why\n" +
                        "Right to access – Request access to your personal data\n" +
                        "Right to object – Withdraw consent or object to data processing\n" +
                        "Right to erasure/blocking – Request deletion of unnecessary or unlawfully processed data\n" +
                        "Right to data portability – Request your data in an accessible format\n" +
                        "Right to file a complaint – With the National Privacy Commission (NPC) if you believe your rights have been violated\n" +
                        "You may exercise your rights by contacting us through the details below.\n" +
                        "\n" +
                        "7. Security Measures\n" +
                        "We implement reasonable and appropriate organizational, physical, and technical measures to:\n" +
                        "Protect personal data against unauthorized access or disclosure\n" +
                        "Prevent data loss, theft, or misuse\n" +
                        "Ensure the confidentiality, integrity, and availability of information\n" +
                        "These include password encryption, access controls, secure data servers, and regular system monitoring.\n" +
                        "\n" +
                        "8. Policy Updates\n" +
                        "We may update this Privacy Policy from time to time to reflect changes in laws, technology, or practices. When significant changes occur, we will notify you through the App or via email.\n" +
                        "Your continued use of IntelliGym after changes are made indicates your acceptance of the updated Privacy Policy.\n" +
                        "\n" +
                        "9. Contact Us\n" +
                        "For any questions, concerns, or requests regarding this Privacy Policy or your personal data, you may contact:\n" +
                        "\n" +
                        "Dok's Gym Support Team\n" +
                        " Email: 422002468@ntc.edu.ph\n" +
                        " Phone: 0961 862 1769\n" +
                        "You may also contact the National Privacy Commission (NPC) at:\n" +
                        " Website: https://privacy.gov.ph\n" +
                        " Email: complaints@privacy.gov.ph\n" +
                        "\n",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = Int.MAX_VALUE,
                overflow = TextOverflow.Visible,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    SystemBackButtonHandler {
        IntelliGymAppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun TermsAndConditionsScreenPreview(){
    TermsAndConditionsScreen()
}