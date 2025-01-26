package com.application.onlineshopecommerce;

import android.content.Intent; import android.os.Bundle;
import android.widget.Button; import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class loginsec extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        db = new database(this);

        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (db.checkUser(userEmail, userPass)) {
                Toast.makeText(loginsec.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), loginsec.class));
            } else {
                Toast.makeText(loginsec.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), registrationapp.class)));
    }
}