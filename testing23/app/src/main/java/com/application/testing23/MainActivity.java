package com.application.testing23;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;
    private Switch switchToggle;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        switchToggle = findViewById(R.id.switchToggle);
        imageView = findViewById(R.id.imageView);

        // Button Click Listener: Update TextView with EditText input
        button.setOnClickListener(v -> textView.setText(editText.getText().toString()));

        // Switch Change Listener: Toggle ImageView Visibility
        switchToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.INVISIBLE);
            }
        });

        // EditText Focus Listener: Change background color on focus
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                editText.setBackgroundColor(Color.LTGRAY);
            } else {
                editText.setBackgroundColor(Color.WHITE);
            }
        });
    }
}