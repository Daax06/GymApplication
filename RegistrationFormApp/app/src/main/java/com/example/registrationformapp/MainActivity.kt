package com.example.registrationformapp

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var etName: EditText? = null
    private var etEmail: EditText? = null
    private var etUsername: EditText? = null
    private var etBirthday: EditText? = null
    private var etPassword: EditText? = null
    private var etConfirmPassword: EditText? = null
    private var btnRegister: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the views
        etName = findViewById<EditText>(R.id.etName)
        etEmail = findViewById<EditText>(R.id.etEmail)
        etUsername = findViewById<EditText>(R.id.etUsername)
        etBirthday = findViewById<EditText>(R.id.etBirthday)
        etPassword = findViewById<EditText>(R.id.etPassword)
        etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        btnRegister = findViewById<Button>(R.id.btnRegister)

        // Set click listener for the Register button
        btnRegister.setOnClickListener(View.OnClickListener { v: View? -> registerUser() })
    }

    private fun registerUser() {
        // Get input values
        val name = etName!!.text.toString().trim { it <= ' ' }
        val email = etEmail!!.text.toString().trim { it <= ' ' }
        val username = etUsername!!.text.toString().trim { it <= ' ' }
        val birthday = etBirthday!!.text.toString().trim { it <= ' ' }
        val password = etPassword!!.text.toString()
        val confirmPassword = etConfirmPassword!!.text.toString()

        // Validate inputs
        if (TextUtils.isEmpty(name)) {
            etName!!.error = "Please enter your name"
            etName!!.requestFocus()
            return
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail!!.error = "Please enter a valid email"
            etEmail!!.requestFocus()
            return
        }

        if (TextUtils.isEmpty(username)) {
            etUsername!!.error = "Please enter a username"
            etUsername!!.requestFocus()
            return
        }

        if (TextUtils.isEmpty(birthday)) {
            etBirthday!!.error = "Please enter your birthday"
            etBirthday!!.requestFocus()
            return
        }

        if (TextUtils.isEmpty(password)) {
            etPassword!!.error = "Please enter a password"
            etPassword!!.requestFocus()
            return
        }

        if (password != confirmPassword) {
            etConfirmPassword!!.error = "Passwords do not match"
            etConfirmPassword!!.requestFocus()
            return
        }

        // Show success message
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
    }
}