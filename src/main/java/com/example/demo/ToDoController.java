package com.example.demo;// Import-Anweisungen, ähnlich wie im BenutzerController
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    // Endpunkt zum Erstellen eines neuen ToDo-Elements
    @PostMapping("/todos")
    public ResponseEntity<ToDoElement> createToDo(@RequestBody ToDoElement toDoElement,
                                                  @RequestHeader("benutzerId") Long benutzerId) {
        toDoElement.setBenutzerId(benutzerId);
        return ResponseEntity.ok(toDoRepository.save(toDoElement));
    }

    // Endpunkt zum Abrufen der ToDo-Liste des angemeldeten Benutzers
    @GetMapping("/todos")
    public ResponseEntity<List<ToDoElement>> getTodos(@RequestHeader("benutzerId") Long benutzerId) {
        List<ToDoElement> todos = toDoRepository.findByBenutzerId(benutzerId);
        return ResponseEntity.ok(todos);
    }
}