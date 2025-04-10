package com.application.testing;

import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
    private final ArrayList<Product> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product) {
        cartItems.add(product);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void removeFromCart(Product product) {
        cartItems.remove(product);
    }
}

