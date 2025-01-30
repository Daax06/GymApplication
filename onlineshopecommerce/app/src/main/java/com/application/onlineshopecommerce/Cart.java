package com.application.onlineshopecommerce;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_checkout); // Ensure this layout has the ListView with ID listViewCheckoutItems

        ListView listViewCart = findViewById(R.id.listViewCheckoutItems);  // Corrected ID for ListView
        Button buttonCheckout = findViewById(R.id.buttonConfirmPurchase); // Corrected ID for Button
        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice); // Added TextView for Total Price

        // Get the list of books from the CartManager
        ArrayList<Product> cartItems = CartManager.getInstance().getCartItems();

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your Cart is empty", Toast.LENGTH_SHORT).show();
        } else {
            // Set up the adapter
            CartAdapter cartAdapter = new CartAdapter(this, cartItems);
            listViewCart.setAdapter(cartAdapter);  // Set the adapter to the ListView

            // Update the total price
            double totalPrice = 0;
            for (Product book : cartItems) {
                totalPrice += book.getPrice();
            }
            textViewTotalPrice.setText("Total: Php " + totalPrice);
        }

        // Handle Checkout Button Click
        buttonCheckout.setOnClickListener(v -> checkout(cartItems));
    }

    private void checkout(ArrayList<Product> cartItems) {
        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your Cart is empty", Toast.LENGTH_SHORT).show();
        } else {
            // Here you can implement the logic to process the order
            // For now, we will just show a message
            Toast.makeText(this, "Your order has been placed!", Toast.LENGTH_SHORT).show();

            // Prepare order details to pass to the Confirmation activity
            StringBuilder orderDetails = new StringBuilder("Order Details:\n");
            for (Product book : cartItems) {
                orderDetails.append(book.getName()).append(" - Php").append(book.getPrice()).append("\n");
            }

            // Clear the Cart after Checkout
            CartManager.getInstance().clearCart();

            // Navigate to ConfirmationActivity
            Intent intent = new Intent(Cart.this, Confirmation.class);
            intent.putExtra("ORDER_DETAILS", orderDetails.toString());
            startActivity(intent);
            finish(); // Close the CartActivity
        }
    }
}
