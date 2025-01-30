package com.application.onlineshopecommerce;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginSec extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new Database(this);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        TextView textViewRegister = findViewById(R.id.textViewRegister);

        buttonLogin.setOnClickListener(v -> login());
        textViewRegister.setOnClickListener(v -> startActivity(new Intent(LoginSec.this, RegistrationApp.class)));
    }

    private void login() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Check if email and password are not empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", new String[]{email, password});

        if (cursor.moveToFirst()) {
            Intent intent = new Intent(LoginSec.this, Homepage.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
}