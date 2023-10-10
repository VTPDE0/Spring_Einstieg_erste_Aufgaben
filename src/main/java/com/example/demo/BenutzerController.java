package com.example.demo;
//change für commit

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/benutzer") // Dies legt den Basispfad für alle Endpunkte in diesem Controller fest.
public class BenutzerController {
    @Autowired
    private BenutzerRepository benutzerRepository;


    //Registrierung
    @PostMapping
    public Benutzer registrieren(@RequestBody Benutzer benutzer) {
        // Speichern des Benutzers in der Datenbank
        return benutzerRepository.save(benutzer);
    }

    // weitere Methoden nicht vergessen, jetzt TODO Liste ist überfordert, deswegen einfach so!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
}
