package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Annotation sagt Spring, dass diese Klasse als Webcontroller dienen soll,
// der Anfragen behandelt und Antworten generiert. Es ist spezifisch für RESTful-Webdienste.
@RestController
public class HelloWorldController {

    //http://localhost:8080/hello
    //Methode helloWorld ist mit @GetMapping("/hello") annotiert, was bedeutet, dass sie auf HTTP GET-Anfragen an die URL /hello reagieren wird.
    // Die Methode helloWorld() gibt den String "Hello World!" zurück.
    //In diesem Kontext bedeutet die Rückgabe eines Strings, dass diese Zeichenkette als Antwort auf die HTTP-Anfrage gesendet wird.
    //Es gibt kein println, weil wir hier nicht auf der Konsole drucken, sondern eine Webantwort senden
    //Aber wie aufrufe ich Methoden eine nach andere? soll ich noch eine Anfrage an localhost schicken, sodass Ausgabe wird aktualisiert?
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}
