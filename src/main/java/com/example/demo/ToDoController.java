package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/list")
    public ResponseEntity<?> getTodosForAuthenticatedUser(HttpServletRequest request) {
        Benutzer authenticatedUser = (Benutzer) request.getAttribute("authenticatedUser");
        List<ToDoElement> todosForUser = toDoRepository.findByBenutzer(authenticatedUser);
        return ResponseEntity.ok(todosForUser);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(HttpServletRequest request, @RequestBody ToDoElement todo) {
        Benutzer authenticatedUser = (Benutzer) request.getAttribute("authenticatedUser");
        todo.setBenutzer(authenticatedUser);
        ToDoElement savedTodo = toDoRepository.save(todo);
        return ResponseEntity.ok(savedTodo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodo(HttpServletRequest request, @PathVariable Long id) {
        Benutzer authenticatedUser = (Benutzer) request.getAttribute("authenticatedUser");
        ToDoElement todo = toDoRepository.findByIdAndBenutzer(id, authenticatedUser);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(HttpServletRequest request,
                                        @PathVariable Long id,
                                        @RequestBody ToDoElement updatedTodo) {
        Benutzer authenticatedUser = (Benutzer) request.getAttribute("authenticatedUser");
        ToDoElement existingTodo = toDoRepository.findByIdAndBenutzer(id, authenticatedUser);
        if (existingTodo == null) {
            return ResponseEntity.notFound().build();
        }
        existingTodo.setText(updatedTodo.getText());
        existingTodo.setErledigt(updatedTodo.isErledigt());
        ToDoElement savedTodo = toDoRepository.save(existingTodo);
        return ResponseEntity.ok(savedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(HttpServletRequest request, @PathVariable Long id) {
        Benutzer authenticatedUser = (Benutzer) request.getAttribute("authenticatedUser");
        ToDoElement todo = toDoRepository.findByIdAndBenutzer(id, authenticatedUser);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        toDoRepository.delete(todo);
        return ResponseEntity.noContent().build();
    }
}