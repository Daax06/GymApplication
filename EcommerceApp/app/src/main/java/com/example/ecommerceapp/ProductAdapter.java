import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private List<Product> fullProductList;
    private Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.fullProductList = new ArrayList<>(productList); // Store the original list for filtering
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Load image using Glide
        Glide.with(context)
                .load(product.getImageUrl())
                .into(holder.productImage);

        // Set product name and price
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder class
    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName, productPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }

    // Update the product list
    public void updateList(List<Product> newList) {
        productList = new ArrayList<>();
        productList.addAll(newList);
        notifyDataSetChanged();
    }
}
