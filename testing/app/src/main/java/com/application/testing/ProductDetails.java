package com.application.testing;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);

        Product product = (Product) getIntent().getSerializableExtra("camera");

        TextView nameTextView = findViewById(R.id.detailName);
        TextView brandTextView = findViewById(R.id.detailBrand);
        TextView priceTextView = findViewById(R.id.detailPrice);
        TextView descriptionTextView = findViewById(R.id.detailDescription);

        nameTextView.setText(product.getName());
        brandTextView.setText(product.getBrand());
        priceTextView.setText("$" + product.getPrice());
        descriptionTextView.setText(product.getDescription());
    }
}
