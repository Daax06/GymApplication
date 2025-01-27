package com.application.onlineshopecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class productdetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        // Initialize the TextViews
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewAuthor = findViewById(R.id.textViewAuthor);
        TextView textViewPrice = findViewById(R.id.textViewPrice);
        TextView textViewDescription = findViewById(R.id.textViewDescription);

        // Initialize the button
        Button buttonAddToCart = findViewById(R.id.buttonAddToCart);

        // Get the Book object from Intent
        product book = (product) getIntent().getSerializableExtra("book");

        // Check if the book object is not null
        if (book != null) {
            textViewTitle.setText(book.getTitle());
            textViewAuthor.setText(book.getAuthor());
            textViewPrice.setText(String.valueOf(book.getPrice()));
            textViewDescription.setText(book.getDescription());

            // Set the OnClickListener for the button
            buttonAddToCart.setOnClickListener(v -> {
                // Add book to cart
                CartManager.getInstance().getCartItems().add(book);

                // Navigate to CartActivity
                Intent intent = new Intent(BookDetailsActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }
    }
}
