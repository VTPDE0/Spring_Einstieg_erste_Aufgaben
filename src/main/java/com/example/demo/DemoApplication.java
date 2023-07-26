package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Die @SpringBootApplication-Annotation sagt Spring,
// dass diese Klasse die Hauptklasse unserer Anwendung ist und dass sie die Konfiguration und das Starten der Anwendung behandeln wird.
@SpringBootApplication
public class DemoApplication {


	//Die Zeilen hier starten die Spring-Anwendung.
	// Es ist nicht so, dass wir hier eine bestimmte Methode aus der HelloWorldController-Klasse aufrufen.
	// Stattdessen starten wir das Spring Framework, das dann auf HTTP-Anfragen reagiert und entsprechend den Anmerkungen in unseren Controller-Klassen handelt.
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
