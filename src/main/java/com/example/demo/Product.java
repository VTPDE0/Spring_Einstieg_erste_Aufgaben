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

    // TODO ich soll noch getter und setter dafür schreiben!

    private String name;  // Der Name des Produkts
    private double price;  // Der Preis des Produkts

    // Standardkonstruktor benötigt von JPA
    public Product() {}

    // Konstruktor mit Parametern
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
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
}
