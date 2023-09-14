package com.example.demo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
/*
curl http://localhost:8080/cart/{cartId}
 */

public class CartController {

    // Eine Map, die Warenkörbe (Listen von Produkten) mit einer eindeutigen ID verbindet
    private Map<Integer, List<Product>> carts = new HashMap<>();


    @GetMapping("/{cartId}")
    public ResponseEntity<List<Product>> getCartProducts(@PathVariable int cartId) {
        // Überprüfen, ob ein Warenkorb mit der angegebenen ID existiert
        List<Product> cartProducts = carts.get(cartId);

        // Wenn es keinen Warenkorb mit der angegebenen ID gibt
        if (cartProducts == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(cartProducts);
    }



    /*
     POST-Endpunkt, um ein Produkt zum Warenkorb hinzuzufügen,
     in cmd braucht man folgender Befehl auszuführen:
     curl -X POST http://localhost:8080/cart/id/addProduct -H "Content-Type: application/json" -d "{\"name\":\"X\",\"price\":0.0}"

     curl -X POST http://localhost:8080/cart/1/addProduct -H "Content-Type: application/json" -d "{\"name\":\"Apfel\",\"price\":0.5}"
     curl -X POST http://localhost:8080/cart/1/addProduct -H "Content-Type: application/json" -d "{\"name\":\"Birne\",\"price\":0.5}"
     curl -X POST http://localhost:8080/cart/2/addProduct -H "Content-Type: application/json" -d "{\"name\":\"Banane\",\"price\":0.3}"
     curl -X POST http://localhost:8080/cart/2/addProduct -H "Content-Type: application/json" -d "{\"name\":\"Kirsche\",\"price\":0.8}"
     */
    @PostMapping("/{cartId}/addProduct")
    public void addProductToCart(@PathVariable int cartId, @RequestBody Product product) {
        // Wenn der Warenkorb noch nicht existiert, wird er erstellt.
        carts.computeIfAbsent(cartId, k -> new ArrayList<>()).add(product);
    }


    /*
    Methode, um den bestimmten gesamten Einkaufswagen zu löschen
    curl -X DELETE http://localhost:8080/cart/id/clear
     */
    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<String> clearCart(@PathVariable int cartId) {
        if (carts.containsKey(cartId)) {
            carts.get(cartId).clear();
            return ResponseEntity.ok("Warenkorb " + cartId + " wurde geleert.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warenkorb mit ID " + cartId + " nicht gefunden.");
        }
    }


    /*
    Methode, um ein bestimmtes Produkt aus bestimmtem Einkaufswagen zu entfernen
    curl -X DELETE http://localhost:8080/cart/1/product/Apfel
     */

    @DeleteMapping("/{cartId}/product/{name}")
    public ResponseEntity<String> removeProductByNameFromCart(@PathVariable int cartId, @PathVariable String name) {
        List<Product> selectedCart = carts.get(cartId);

        // Wenn der Warenkorb nicht existiert wird entsprechende Antwort gegeben
        if (selectedCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warenkorb mit ID " + cartId + " nicht gefunden.");
        }

        Product productToRemove = null;

        for (Product product : selectedCart) {
            if (product.getName().equalsIgnoreCase(name)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            selectedCart.remove(productToRemove);
            return ResponseEntity.ok(productToRemove.getName() + " wurde aus dem Einkaufswagen " + cartId + " entfernt.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produktname nicht im Einkaufswagen " + cartId + " gefunden.");
    }


}
