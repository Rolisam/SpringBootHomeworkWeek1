package com.homework.week2.service;


import com.homework.week2.model.Product;
import com.homework.week2.repository.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.stream.Collectors;


@Profile("Plus")
@Service
public class ShopPlusService{

    ShoppingCart shoppingCart;

    @Value("${app-vat}")
    double tax;

    @Autowired
    public ShopPlusService(ShoppingCart shoppingCart) {
        this.shoppingCart=shoppingCart;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        shoppingCart.getProductList().forEach(o -> System.out.println(o.getName() + " " + o.getPrice()));
        Double shoppingCartValue = getShoppingCartValue();
        Double valueWithTax = getValueWithTax(shoppingCartValue);
        System.out.println("Shopping cart value: " + shoppingCartValue + " zł \nWith " + tax +"% tax: " +  valueWithTax + " zł");
    }

    private Double getShoppingCartValue() {
        return Math.floor(shoppingCart.getProductList().stream().mapToDouble(Product::getPrice).sum() * 100) / 100;
    }

    private Double getValueWithTax(Double sum) {
        double valueWithTax = sum + (sum * (tax/100));
        return Math.floor((valueWithTax) * 100) / 100;
    }
}
