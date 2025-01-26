import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private TextView totalPrice;
    private Button checkoutButton;
    private List<Product> cartItems;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPrice = findViewById(R.id.totalPrice);
        checkoutButton = findViewById(R.id.checkoutButton);

        // Example cart items (you will load these from the database)
        cartItems = new ArrayList<>();
        cartItems.add(new Product("Product 1", 10.99));
        cartItems.add(new Product("Product 2", 15.49));

        cartAdapter = new CartAdapter(this, cartItems);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        updateTotalPrice();

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
            }
        });
    }

    private void updateTotalPrice() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        totalPrice.setText("Total: $" + total);
    }
}
