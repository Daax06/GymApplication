package com.application.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView textViewConfirmationMessage = findViewById(R.id.textViewConfirmationMessage);
        TextView textViewOrderDetails = findViewById(R.id.textViewOrderDetails);
        Button buttonBackToHome = findViewById(R.id.buttonBackToHome);

        // Optionally, you can pass order details from the CartActivity
        String orderDetails = getIntent().getStringExtra("ORDER_DETAILS");
        if (orderDetails != null) {
            textViewOrderDetails.setText(orderDetails);
        }

        buttonBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the MainActivity
                Intent intent = new Intent(Confirmation.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the ConfirmationActivity
            }
        });
    }
}