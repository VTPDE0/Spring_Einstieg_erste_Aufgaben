package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // Diese Klasse ist eine JPA-Entity
public class Product {

    @Id  // Markiert dieses Feld als den Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Der Wert wird automatisch generiert dank GenerationType aus javax.persistence
    private Long id;  // Die ID des Produkts

    private String name;  // Der Name des Produkts
    private double price;  // Der Preis des Produkts

    // Standardkonstruktor benötigt von JPA
    // Hinzufügen einer cartId, um SQL Abfrage DELETE FROM product WHERE cart_id = <cartId>; funktionierend machen (früher wir hätten nicht CartID)
    private Long cartId;

    public Product() {}

    public Product(String name, double price, Long cartId) {
        this.name = name;
        this.price = price;
        this.cartId = cartId;
    }

    // Getter und Setter für cartId
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }


    // Getter und Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
}
