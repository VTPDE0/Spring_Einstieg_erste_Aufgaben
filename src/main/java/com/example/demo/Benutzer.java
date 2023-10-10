package com.example.demo;
//change für commit

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String benutzername;

    private String passwort;

    // Standardkonstruktor, der von JPA benötigt wird
    public Benutzer() {}

    // Konstruktor mit Parametern
    public Benutzer(String benutzername, String passwort) {
        this.benutzername = benutzername;
        this.passwort = passwort;
    }
    //Getter und Setter
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getBenutzername() {
        return benutzername;
    }


    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
