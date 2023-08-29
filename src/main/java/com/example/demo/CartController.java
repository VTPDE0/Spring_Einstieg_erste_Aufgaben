package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    // Ein einfacher Warenkorb als Liste von Produkten
    private List<Product> cart = new ArrayList<>();

    // GET-Endpunkt, um Produkte im Warenkorb abzurufen
    @GetMapping
    public List<Product> getCart() {
        return cart;
    }

    // POST-Endpunkt, um ein Produkt zum Warenkorb hinzuzufügen
    @PostMapping
    public void addProduct(@RequestBody Product product) {
        cart.add(product);
    }
}
