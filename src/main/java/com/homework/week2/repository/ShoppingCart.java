package com.homework.week2.repository;

import com.homework.week2.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class ShoppingCart {

    private List<Product> productList = new ArrayList<>();

    public ShoppingCart() {
        addProductsToShoppingCart();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Double getRandomPrice() {
        return Math.floor(ThreadLocalRandom.current().nextDouble(50, 300) * 100) / 100;
    }

    private void addProductsToShoppingCart() {
        productList.add(new Product("Test 1", getRandomPrice()));
        productList.add(new Product("Test 2", getRandomPrice()));
        productList.add(new Product("Test 3", getRandomPrice()));
        productList.add(new Product("Test 4", getRandomPrice()));
        productList.add(new Product("Test 5", getRandomPrice()));
    }
}
