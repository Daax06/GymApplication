package com.application.testing;

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

public class Checkout extends AppCompatActivity {

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
        ArrayList<Product> cartItems = CartManager.getInstance().getCartItems();

        // Check if the Cart is empty
        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your Cart is empty", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if the Cart is empty
            return;
        }

        // Set up the adapter for the ListView
        ArrayAdapter<Product> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, cartItems) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textViewTitle = view.findViewById(android.R.id.text1);
                TextView textViewPrice = view.findViewById(android.R.id.text2);

                Product book = getItem(position);
                if (book != null) {
                    textViewTitle.setText(book.getName());
                    textViewPrice.setText(String.format("$%.2f", book.getPrice()));
                }
                return view;
            }
        };
        listViewCheckoutItems.setAdapter(adapter);

        // Calculate total price
        double total = 0;
        for (Product book : cartItems) {
            total += book.getPrice();
        }
        textViewTotalPrice.setText(String.format("Total: $%.2f", total));

        // Handle Confirm Purchase button click
        buttonConfirmPurchase.setOnClickListener(v -> {
            // Handle purchase Confirmation logic here
            Toast.makeText(this, "Purchase confirmed!", Toast.LENGTH_SHORT).show();

            // Clear the Cart after purchase
            CartManager.getInstance().clearCart();

            // Navigate to ConfirmationActivity
            Intent intent = new Intent(Checkout.this, Confirmation.class);
            startActivity(intent); // Start the Confirmation activity

            finish(); // Close the current activity
        });
    }
}
