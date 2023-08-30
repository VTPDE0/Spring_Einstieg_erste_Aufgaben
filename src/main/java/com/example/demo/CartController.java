package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    // Ein Warenkorb als Liste von Produkten
    private List<Product> cart = new ArrayList<>();

    // GET-Endpunkt, um Produkte im Warenkorb abzurufen
    @GetMapping
    public List<Product> getCart() {
        return cart;
    }

    // POST-Endpunkt, um ein Produkt zum Warenkorb hinzuzufügen,
    // in cmd braucht man folgender Befehl auszuführen:
    // curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"Apfel\",\"price\":0.5}" http://localhost:8080/cart
    @PostMapping
    public void addProduct(@RequestBody Product product) {
        cart.add(product);
    }
}
