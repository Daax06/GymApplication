package com.application.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Product> {
    public CartAdapter(Context context, ArrayList<Product> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Product book = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2 , parent, false);
        }

        TextView textViewTitle = convertView.findViewById(android.R.id.text1);
        TextView textViewPrice = convertView.findViewById(android.R.id.text2);

        if (book != null) {
            textViewTitle.setText(book.getName());
            textViewPrice.setText(String.valueOf(book.getPrice()));
        }

        return convertView;
    }
}
