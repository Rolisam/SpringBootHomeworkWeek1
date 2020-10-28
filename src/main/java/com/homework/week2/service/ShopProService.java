package com.homework.week2.service;


import com.homework.week2.model.Product;
import com.homework.week2.repository.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


@Profile("Pro")
@Service
public class ShopProService {

    private ShoppingCart shoppingCart;

    @Value("${app-tax}")
    private double tax;

    @Value("${app-discount}")
    private double discount;

    @Autowired
    public ShopProService(ShoppingCart shoppingCart) {
        this.shoppingCart=shoppingCart;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        Double shoppingCartValue = getShoppingCartValue();
        Double valueWithTax = getValueWithTax(shoppingCartValue);
        Double valueWithDiscount = getValueWithDiscount(valueWithTax);

        shoppingCart.getProductList().forEach(o -> System.out.println(o.getName() + " " + o.getPrice()));
        System.out.println("Shopping cart value: " + shoppingCartValue + " zł \nWith " + tax +"% tax: "
                +  valueWithTax + " zł\nWith " + discount + "% discount: " + valueWithDiscount);
    }

    private Double getShoppingCartValue() {
        return Math.floor(shoppingCart.getProductList().stream().mapToDouble(Product::getPrice).sum() * 100) / 100;
    }

    private Double getValueWithTax(Double sum) {
        double valueWithTax = sum + (sum * (tax/100));
        return Math.floor(valueWithTax * 100) / 100;
    }

    private Double getValueWithDiscount(Double sum) {
        double valueWithDiscount = sum - (sum * (discount / 100));
        return Math.floor(valueWithDiscount * 100) / 100;
    }
}
