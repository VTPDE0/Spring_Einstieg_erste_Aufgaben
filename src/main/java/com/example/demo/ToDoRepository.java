package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Das Interface erweitert JpaRepository wo CRUD-Methoden für die ToDoElemente bereits fertig sind.
@Repository //obwohl man muss nicht wirklich hier diese Annotation machen, da diese Klasse von JpaRepository (das Gleiche gilt auch für einer seiner Unterinterfaces) erbt
public interface ToDoRepository extends JpaRepository<ToDoElement, Long> {

    List<ToDoElement> findByBenutzerId(Long benutzerId);
    List<ToDoElement> findByBenutzer(Benutzer benutzer);
    ToDoElement findByIdAndBenutzer(Long id, Benutzer benutzer);

    Optional<ToDoElement> findByIdAndBenutzerId(Long id, Long benutzerId);
    // Eine Methode, um alle ToDo-Elemente für einen bestimmten Benutzer basierend auf der benutzerId abzurufen.
}
