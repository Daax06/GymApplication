package com.example.loginactivityapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etEmail, etUsername, etBirthday, etPassword, etConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etBirthday = findViewById(R.id.etBirthday);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Set click listener for the Register button
        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        // Get input values
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String birthday = etBirthday.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        // Validate inputs
        if (TextUtils.isEmpty(name)) {
            etName.setError("Please enter your name");
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Please enter a username");
            etUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(birthday)) {
            etBirthday.setError("Please enter your birthday");
            etBirthday.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter a password");
            etPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return;
        }

        // Show success message
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
    }
}
