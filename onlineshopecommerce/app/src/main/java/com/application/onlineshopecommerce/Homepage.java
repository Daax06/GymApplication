package com.application.onlineshopecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Homepage extends AppCompatActivity {
    private List<Product> cameraList;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Set up Toolbar as a navigation bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder
                (R.id.nav_home, R.id.nav_cart, R.id.nav_profile)
                .build();

        // Initialize NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_homepage);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Set up ListView and ProductAdapter
        ListView listViewCameras = findViewById(R.id.listView);
        cameraList = new ArrayList<>();

        // Adding camera products
        cameraList.add(new Product(1, "Canon EOS R5", "Canon", 3899, "High-resolution mirrorless camera with advanced autofocus"));
        cameraList.add(new Product(2, "Sony Alpha a7 III", "Sony", 1999, "Full-frame mirrorless camera for professionals"));
        cameraList.add(new Product(3, "Nikon Z6 II", "Nikon", 1999, "Versatile mirrorless camera with excellent low-light performance"));
        cameraList.add(new Product(4, "Fujifilm X-T4", "Fujifilm", 1699, "Compact mirrorless camera with in-body stabilization"));
        cameraList.add(new Product(5, "Panasonic Lumix S5", "Panasonic", 1799, "Lightweight full-frame mirrorless camera"));

        adapter = new ProductAdapter(this, cameraList);
        listViewCameras.setAdapter(adapter);

        // Set up item click listener to show product details
        listViewCameras.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedCamera = (Product) parent.getItemAtPosition(position);
            Intent intent = new Intent(Homepage.this, ProductDetails.class);
            intent.putExtra("camera", selectedCamera);
            startActivity(intent);
        });

        // Set up search functionality
        EditText searchInput = findViewById(R.id.searchInput);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCameras(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterCameras(String query) {
        List<Product> filteredList = cameraList.stream()
                .filter(camera -> camera.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        adapter.updateData(filteredList);
    }
}