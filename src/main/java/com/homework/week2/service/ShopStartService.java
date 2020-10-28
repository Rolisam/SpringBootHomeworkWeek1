package com.homework.week2.service;

import com.homework.week2.model.Product;
import com.homework.week2.repository.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


@Service
@Profile("Start")
public class ShopStartService {

   private ShoppingCart shoppingCart;

   @Autowired
   public ShopStartService(ShoppingCart shoppingCart) {
      this.shoppingCart = shoppingCart;
   }

   @EventListener(ApplicationReadyEvent.class)
   public void get() {
      shoppingCart.getProductList().forEach(o -> System.out.println(o.getName() + " " + o.getPrice()));
      System.out.println("Shopping cart value: " + getShoppingCartValue() + " zł");
   }

   private Double getShoppingCartValue() {
      return Math.floor(shoppingCart.getProductList().stream().mapToDouble(Product::getPrice).sum() * 100) / 100;
   }
}
