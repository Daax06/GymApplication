package com.application.testing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
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
        setContentView(R.layout.activity_checkout);

        ListView listViewCart = findViewById(R.id.listViewCheckoutItems);
        Button buttonCheckout = findViewById(R.id.buttonConfirmPurchase);
        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice);

        ArrayList<Product> cartItems = CartManager.getInstance().getCartItems();

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your Cart is empty", Toast.LENGTH_SHORT).show();
        } else {
            CartAdapter cartAdapter = new CartAdapter(this, cartItems);
            listViewCart.setAdapter(cartAdapter);

            // Update total price
            double totalPrice = 0;
            for (Product product : cartItems) {
                totalPrice += product.getPrice();
            }
            textViewTotalPrice.setText("Total: Php " + totalPrice);
        }

        buttonCheckout.setOnClickListener(v -> checkout(cartItems));
    }

    private void checkout(ArrayList<Product> cartItems) {
        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your Cart is empty", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Your order has been placed!", Toast.LENGTH_SHORT).show();

            StringBuilder orderDetails = new StringBuilder("Order Details:\n");
            for (Product product : cartItems) {
                orderDetails.append(product.getName()).append(" - Php").append(product.getPrice()).append("\n");
            }

            CartManager.getInstance().clearCart();

            Intent intent = new Intent(Cart.this, Confirmation.class);
            intent.putExtra("ORDER_DETAILS", orderDetails.toString());
            startActivity(intent);
            finish();
        }
    }
}

