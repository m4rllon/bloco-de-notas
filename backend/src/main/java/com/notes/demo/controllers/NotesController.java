package com.notes.demo.controllers;

import com.notes.demo.assemblers.NotesModelAssembler;
import com.notes.demo.domain.notes.Notes;
import com.notes.demo.services.NotesService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    private final NotesService notesService;
    private final NotesModelAssembler assembler;

    public NotesController(NotesService notesService, NotesModelAssembler assembler) {
        this.notesService = notesService;
        this.assembler = assembler;
    }

    @GetMapping()
    public CollectionModel<EntityModel<Notes>> getAllNotes(){
        List<Notes> notesList = notesService.getAllNotes();

        List<EntityModel<Notes>> notesEntityModel = notesList.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(notesEntityModel);
    }

    @GetMapping("/{username}")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername())")
    public CollectionModel<EntityModel<Notes>> getNotesByUser(@PathVariable String username){
        List<Notes> notesList = notesService.getAllNotesByUsername(username);

        List<EntityModel<Notes>> notesEntityModel = notesList.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(notesEntityModel);
    }

//    @PostMapping("/new")
//    public ResponseEntity<EntityModel<Notes>> createNotes(@Valid @RequestBody NotesDTO newNotesDTO){
//        Notes newNotes = notesService.createNotes(newNotesDTO);
//        return ResponseEntity.ok(assembler.toModel(newNotes));
//    }
}
