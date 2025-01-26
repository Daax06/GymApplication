package com.example.registrationapp;

import android.content.Intent; import android.os.Bundle;
import android.widget.Button; import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (db.loginUser(email, password)) {
                startActivity(new Intent(ActivityLogin.this, Home.class));
                finish();
            } else {
                Toast.makeText(ActivityLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegister.setOnClickListener(view -> startActivity(new Intent(ActivityLogin.this, RegistrationActivity.class)));
    }
}