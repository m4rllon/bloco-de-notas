package com.notes.demo.controllers;

import com.notes.demo.models.Notes;
import com.notes.demo.repositories.NotesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    private final NotesRepository repository;

    public NotesController(NotesRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Notes createNotes(@RequestBody Notes notes){
        return repository.save(notes);
    }
}
