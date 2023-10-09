package com.example.demo;// Import-Anweisungen für benötigte Klassen und Annotationen
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Das Interface erweitert JpaRepository, wodurch CRUD-Methoden für die ToDoElement-Entität bereitgestellt werden.
public interface ToDoRepository extends JpaRepository<ToDoElement, Long> {

    // Eine Methode, um alle ToDo-Elemente für einen bestimmten Benutzer basierend auf der benutzerId abzurufen.
    List<ToDoElement> findByBenutzerId(Long benutzerId);
}
