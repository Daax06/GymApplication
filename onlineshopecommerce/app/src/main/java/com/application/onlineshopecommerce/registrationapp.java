package com.application.onlineshopecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registrationapp extends AppCompatActivity {


    EditText etName, etEmail, etPassword;
    Button btnregister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationapp);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnregister = findViewById(R.id.btn_register);
        db = new DatabaseHelper(this);

        btnregister.setOnClickListener(View -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (db.registerUser(name, email, password)) {
                Toast.makeText(registrationapp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(registrationapp.this, registrationapp.class));
                finish();
            } else {
                Toast.makeText(registrationapp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(registrationapp.this, "Passwords Do Not Match!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
