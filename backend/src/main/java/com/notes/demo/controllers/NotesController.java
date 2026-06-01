package com.notes.demo.controllers;

import com.notes.demo.assemblers.NotesModelAssembler;
import com.notes.demo.domain.notes.Notes;
import com.notes.demo.dtos.NotesDTO;
import com.notes.demo.services.NotesService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public CollectionModel<EntityModel<Notes>> getNotesByUser(@PathVariable String username){
        List<Notes> notesList = notesService.getAllNotesByUsername(username);

        List<EntityModel<Notes>> notesEntityModel = notesList.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(notesEntityModel);
    }

    @PostMapping("/{username}")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public ResponseEntity<EntityModel<Notes>> createNotes(
            @PathVariable String username, @RequestBody NotesDTO notesDTO, @AuthenticationPrincipal UserDetails currentUser){
        try{
            var newNotes = new Notes(
                    notesDTO.getTitle(),
                    notesDTO.getBody(),
                    LocalDateTime.now(),
                    currentUser
            );
            var notes = notesService.createNotes(newNotes);
            return ResponseEntity.ok(assembler.toModel(notes));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{username}/{notesID}")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public ResponseEntity<EntityModel<Notes>> deleteNotes(@PathVariable String username, @PathVariable Long notesID){
        try{
            notesService.deleteNotes(notesID, username);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
