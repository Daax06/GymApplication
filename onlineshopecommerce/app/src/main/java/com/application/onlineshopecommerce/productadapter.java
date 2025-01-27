package com.application.onlineshopecommerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class productadapter extends ArrayAdapter<product> {
    public productadapter(Context context, List<product> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        product book = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }
        TextView textViewTitle = convertView.findViewById(android.R.id.text1);
        TextView textViewAuthor = convertView.findViewById(android.R.id.text2);

        assert book != null;
        textViewTitle.setText(book.getTitle());
        textViewAuthor.setText(book.getAuthor());

        return convertView;
    }
}