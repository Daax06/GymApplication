import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize product list
        productList = new ArrayList<>();
        productList.add(new Product("Product 1", "$15.99", "https://example.com/product1.jpg"));
        productList.add(new Product("Product 2", "$25.99", "https://example.com/product2.jpg"));
        productList.add(new Product("Product 3", "$35.99", "https://example.com/product3.jpg"));
        productList.add(new Product("Product 4", "$45.99", "https://example.com/product4.jpg"));
        // Add more products as needed

        // Set up adapter
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        // Set up search functionality
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            String query = searchBar.getText().toString().trim();
            if (!query.isEmpty()) {
                filterProducts(query);
            } else {
                Toast.makeText(HomepageActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    // Filter the products by name
    private void filterProducts(String query) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }

        // Update the RecyclerView with the filtered list
        productAdapter.updateList(filteredList);
    }
}
