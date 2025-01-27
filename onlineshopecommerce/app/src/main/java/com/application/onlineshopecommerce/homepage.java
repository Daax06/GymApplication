package com.application.onlineshopecommerce;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class homepage extends AppCompatActivity {
    private List<product> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Set up ListView and BookAdapter
        ListView listViewBooks = findViewById(R.id.listView);
        bookList = new ArrayList<>();

        // Adding manga titles with prices 350+
        bookList.add(new product(1, "ATOMIC HABITS (October 2018)", "James Clear", 499, "An Easy & Proven Way to Build Good Habits & Breaks Bad Ones"));
        bookList.add(new product(2, "Stick with it (June 2017) ", "Sean Young phD", 239, "A Scientifically Proven Process for Changing your Life - for Good"));
        bookList.add(new product(3, "The Definitive Book of Body Language  (2004)", "Allan and Barbara Pease", 399, "How to read others' attitude by their gestures "));
        bookList.add(new product(4, "The Comfort Zone (August 2023)", "Kristen Butler", 299, "Create a Life You Really Love with Less Stress and More Flow"));
        bookList.add(new product(5, "MISEDUCATED: A memoir (June 2021) ", "Brandon P. Fleming and Cornel West", 399, "The only way to create change is to challenge the status quo and push against the boundaries of what is Possible"));
        bookList.add(new product(6, "The Practice of Not Thinking (June 2021)", "Ryunosuke Koike", 199, "offers valuable insights into the nature of thought and the importance of mindfulness. By embracing stillness, letting go of overthinking, and cultivating a non-judgmental awareness, readers can navigate life with greater ease and fulfillment. "));
        bookList.add(new product(7, "Fear Less: How to Win Your Way in Work and Life (July 2020)", "Dr. Pippa Grange", 599, "isn't just about achieving success. It's a guide to transforming your relationship with fear, unlocking your true potential, and finding fulfillment in all areas of life."));
        bookList.add(new product(8, "How to Listen with Intention (June 2020) ", "Patrick King", 499, "The Foundation of True Connection, Communication, & Relationships"));
        bookList.add(new product(9, "Real Change (September 2020)", "Sharon Salzberg", 429, "is a deeply insightful book that offers both personal and collective approaches to creating a more compassionate world."));
        bookList.add(new product(10, "When Life Is Hard (December 2009)", "James MacDonald", 499, "a book that helps readers understand how to respond to trials and find hope in God's promises"));

        // Set up the adapter for the ListView
        productadapter adapter = new productadapter(this, bookList);
        listViewBooks.setAdapter(adapter);

        // Set up item click listener to show book details
        listViewBooks.setOnItemClickListener((parent, view, position, id) -> {
            product selectedBook = bookList.get(position);
            Intent intent = new Intent(homepage.this, productdetails.class);
            intent.putExtra("book", selectedBook);
            startActivity(intent);
        });

        // Handle "View Cart" button click
        Button viewCartButton = findViewById(R.id.viewCartButton);
        viewCartButton.setOnClickListener(v -> {
            // Navigate to CartActivity
            Intent intent = new Intent(homepage.this, cart.class);
            startActivity(intent);
        });
    }
}