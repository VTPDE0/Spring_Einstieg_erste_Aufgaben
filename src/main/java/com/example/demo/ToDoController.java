package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository todoRepository;

    @Autowired
    private BenutzerRepository benutzerRepository;

    // Erstellt ein neues ToDo-Element für den angemeldeten Benutzer
    @PostMapping
    public ToDoElement erstellen(@RequestBody ToDoElement todo, @RequestHeader("benutzerId") Long benutzerId) {
        Optional<Benutzer> benutzer = benutzerRepository.findById(benutzerId);
        if (benutzer.isPresent()) {
            todo.setBenutzer(benutzer.get());
            return todoRepository.save(todo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benutzer nicht gefunden");
        }
    }

    // Gibt die ToDo-Liste des angemeldeten Benutzers zurück
    @GetMapping
    public List<ToDoElement> alleTodos(@RequestHeader("benutzerId") Long benutzerId) {
        return todoRepository.findByBenutzerId(benutzerId);
    }

    // Gibt ein bestimmtes ToDo-Element des angemeldeten Benutzers anhand seiner ID zurück
    @GetMapping("/{id}")
    public ToDoElement getTodoById(@PathVariable Long id, @RequestHeader("benutzerId") Long benutzerId) {
        return todoRepository.findByIdAndBenutzerId(id, benutzerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ToDo nicht gefunden"));
    }

    // Aktualisiert ein bestimmtes ToDo-Element des angemeldeten Benutzers anhand seiner ID
    @PutMapping("/{id}")
    public ToDoElement updateTodo(@PathVariable Long id, @RequestBody ToDoElement updatedTodo, @RequestHeader("benutzerId") Long benutzerId) {
        return todoRepository.findByIdAndBenutzerId(id, benutzerId).map(todo -> {
            todo.setText(updatedTodo.getText());
            todo.setErledigt(updatedTodo.isErledigt());
            return todoRepository.save(todo);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ToDo nicht gefunden"));
    }

    // Löscht ein bestimmtes ToDo-Element des angemeldeten Benutzers anhand seiner ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id, @RequestHeader("benutzerId") Long benutzerId) {
        return todoRepository.findByIdAndBenutzerId(id, benutzerId).map(todo -> {
            todoRepository.delete(todo);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ToDo nicht gefunden"));
    }
}
