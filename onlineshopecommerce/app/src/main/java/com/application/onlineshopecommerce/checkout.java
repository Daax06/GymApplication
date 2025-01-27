package com.application.onlineshopecommerce;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class checkout extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize views
        ListView listViewCheckoutItems = findViewById(R.id.listViewCheckoutItems);
        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        Button buttonConfirmPurchase = findViewById(R.id.buttonConfirmPurchase);

        // Get the list of books from the CartManager
        ArrayList<product> cartItems = cartmanager.getInstance().getCartItems();

        // Check if the cart is empty
        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if the cart is empty
            return;
        }

        // Set up the adapter for the ListView
        ArrayAdapter<product> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, cartItems) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textViewTitle = view.findViewById(android.R.id.text1);
                TextView textViewPrice = view.findViewById(android.R.id.text2);

                product book = getItem(position);
                if (book != null) {
                    textViewTitle.setText(book.getTitle());
                    textViewPrice.setText(String.format("$%.2f", book.getPrice()));
                }
                return view;
            }
        };
        listViewCheckoutItems.setAdapter(adapter);

        // Calculate total price
        double total = 0;
        for (product book : cartItems) {
            total += book.getPrice();
        }
        textViewTotalPrice.setText(String.format("Total: $%.2f", total));

        // Handle Confirm Purchase button click
        buttonConfirmPurchase.setOnClickListener(v -> {
            // Handle purchase confirmation logic here
            Toast.makeText(this, "Purchase confirmed!", Toast.LENGTH_SHORT).show();

            // Clear the cart after purchase
            cartmanager.getInstance().clearCart();

            // Navigate to ConfirmationActivity
            Intent intent = new Intent(checkout.this, confirmation.class);
            startActivity(intent); // Start the confirmation activity

            finish(); // Close the current activity
        });
    }
}
