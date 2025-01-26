import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private EditText cardNumberInput, expiryDateInput, cvvInput;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        cardNumberInput = findViewById(R.id.cardNumberInput);
        expiryDateInput = findViewById(R.id.expiryDateInput);
        cvvInput = findViewById(R.id.cvvInput);
        confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckoutActivity.this, "Payment Successful!", Toast.LENGTH_SHORT).show();
                finish(); // Close activity after confirmation
            }
        });
    }
}
