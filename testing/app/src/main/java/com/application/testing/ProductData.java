package com.application.testing;

import com.application.testing.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductData {
    public static List<Product> getProductList() {
        List<Product> cameraList = new ArrayList<>();

        cameraList.add(new Product(1, "Canon EOS R5", "Canon", 3899, "High-resolution mirrorless camera with advanced autofocus"));
        cameraList.add(new Product(2, "Sony Alpha a7 III", "Sony", 1999, "Full-frame mirrorless camera for professionals"));
        cameraList.add(new Product(3, "Nikon Z6 II", "Nikon", 1999, "Versatile mirrorless camera with excellent low-light performance"));
        cameraList.add(new Product(4, "Fujifilm X-T4", "Fujifilm", 1699, "Compact mirrorless camera with in-body stabilization"));
        cameraList.add(new Product(5, "Panasonic Lumix S5", "Panasonic", 1799, "Lightweight full-frame mirrorless camera"));

        return cameraList;
    }
}
