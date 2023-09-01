package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
/*
curl http://localhost:8080/cart
 */

public class CartController {

    // Ein Warenkorb als Liste von Produkten
    private List<Product> cart = new ArrayList<>();

    // GET-Endpunkt, um Produkte im Warenkorb abzurufen
    @GetMapping
    public List<Product> getCart() {
        return cart;
    }

    /*
     POST-Endpunkt, um ein Produkt zum Warenkorb hinzuzufügen,
     in cmd braucht man folgender Befehl auszuführen:
     curl -X POST http://localhost:8080/cart -H "Content-Type: application/json" -d "{\"name\":\"Apfel\",\"price\":0.5}"
     curl -X POST http://localhost:8080/cart -H "Content-Type: application/json" -d "{\"name\":\"Birne\",\"price\":0.5}"
     curl -X POST http://localhost:8080/cart -H "Content-Type: application/json" -d "{\"name\":\"Banane\",\"price\":0.3}"
     curl -X POST http://localhost:8080/cart -H "Content-Type: application/json" -d "{\"name\":\"Kirsche\",\"price\":0.8}"
     */
    @PostMapping
    public void addProduct(@RequestBody Product product) {
        cart.add(product);
    }

    /*
    Methode, um den gesamten Einkaufswagen zu löschen
    curl http://localhost:8080/cart/clear
     */
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        cart.clear(); // Den Einkaufswagen leeren
        return ResponseEntity.ok("Einkaufswagen wurde geleert.");
    }

    /*
    Methode, um ein bestimmtes Produkt aus dem Einkaufswagen zu entfernen
    curl -X DELETE http://localhost:8080/cart/product/Apfel
     */

    @DeleteMapping("/product/{name}")
    public ResponseEntity<String> removeProductByName(@PathVariable String name) {
        Product productToRemove = null;
        for (Product product : cart) {
            if (product.getName().equalsIgnoreCase(name)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            cart.remove(productToRemove);
            return ResponseEntity.ok(productToRemove.getName() + " wurde aus dem Einkaufswagen entfernt.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produktname nicht im Einkaufswagen gefunden.");
    }

}
