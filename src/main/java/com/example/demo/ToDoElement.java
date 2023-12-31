package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;

@Entity  // Diese Klasse ist eine Entität und wird mit einer Datenbanktabelle verknüpft. Warum den sagt mir Konsole dass noch keine TODO Tabelle gibt es?
public class ToDoElement {

    @Id  // Dieses Feld ist der Primärschlüssel.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Der Wert für dieses Feld wird automatisch generiert.
    private Long id;  // ID für jedes ToDo-Element.

    private String text;  // Der Text des ToDo-Elements.

    private boolean erledigt;  // Gibt an, ob das ToDo-Element erledigt war oder noch nicht.


    @ManyToOne
    @JoinColumn(name = "benutzer_id")
    private Benutzer benutzer;

    // Standardkonstruktor der von JPA benötigt ist.
    public ToDoElement() {}

    // Konstruktor mit Parametern.
    public ToDoElement(String text, boolean erledigt, Long benutzerId) {
        this.text = text;
        this.erledigt = erledigt;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public boolean isErledigt() {
        return erledigt;
    }


    public void setErledigt(boolean erledigt) {
        this.erledigt = erledigt;
    }


    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }
}
