package com.application.bikecourier;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registration extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etConfirmPassword;
    Button btnRegister;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize views
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password); // Add confirm password field
        btnRegister = findViewById(R.id.btn_register);
        db = new database(this);

        // Set button click listener
        btnRegister.setOnClickListener(view -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            // Validate inputs
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(registration.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                Toast.makeText(registration.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Register user in the database
            if (db.registerUser(name, email, password)) {
                Toast.makeText(registration.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                // Navigate to the login or home screen
                startActivity(new Intent(registration.this, login.class));
                finish();
            } else {
                Toast.makeText(registration.this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
