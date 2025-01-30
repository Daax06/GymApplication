package com.application.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        super(context, R.layout.activity_product, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_productadapter, parent, false);
        }

        Product product = productList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.productName);
        TextView brandTextView = convertView.findViewById(R.id.productBrand);
        TextView priceTextView = convertView.findViewById(R.id.productPrice);

        nameTextView.setText(product.getName());
        brandTextView.setText(product.getBrand());
        priceTextView.setText("$" + product.getPrice());

        return convertView;
    }

    public void updateData(List<Product> newProductList) {
        productList.clear();
        productList.addAll(newProductList);
        notifyDataSetChanged();
    }
}
