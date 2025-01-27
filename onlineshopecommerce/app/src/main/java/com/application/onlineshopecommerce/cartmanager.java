package com.application.onlineshopecommerce;

import java.util.ArrayList;

public class cartmanager {
    private static cartmanager   instance;
    private final ArrayList<product> cartItems;

    private cartmanager() {
        cartItems = new ArrayList<>();
    }

    public static cartmanager getInstance() {
        if (instance == null) {
            instance = new cartmanager();
        }
        return instance;
    }

    public ArrayList<product> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
