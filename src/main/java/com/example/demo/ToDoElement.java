package com.example.demo;
//change für commit

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // Diese Klasse ist eine Entität und wird mit einer Datenbanktabelle verknüpft. Warum den sagt mir Konsole dass noch keine TODO Tabelle gibt es?
public class ToDoElement {

    @Id  // Dieses Feld ist der Primärschlüssel.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Der Wert für dieses Feld wird automatisch generiert.
    private Long id;  // ID für jedes ToDo-Element.

    private String text;  // Der Text des ToDo-Elements.

    private boolean erledigt;  // Gibt an, ob das ToDo-Element erledigt war oder noch nicht.

    private Long benutzerId;  // Die ID des Benutzers, zu dem dieses ToDo-Element gehört.

    // Standardkonstruktor der von JPA benötigt ist.
    public ToDoElement() {}

    // Konstruktor mit Parametern.
    public ToDoElement(String text, boolean erledigt, Long benutzerId) {
        this.text = text;
        this.erledigt = erledigt;
        this.benutzerId = benutzerId;
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


    public Long getBenutzerId() {
        return benutzerId;
    }


    public void setBenutzerId(Long benutzerId) {
        this.benutzerId = benutzerId;
    }
}
