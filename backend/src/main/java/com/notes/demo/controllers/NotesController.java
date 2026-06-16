package com.notes.demo.controllers;

import com.notes.demo.assemblers.NotesModelAssembler;
import com.notes.demo.domain.notes.ListNotesDTO;
import com.notes.demo.domain.notes.Notes;
import com.notes.demo.domain.notes.NotesDTO;
import com.notes.demo.domain.notes.NotesResponse;
import com.notes.demo.services.NotesService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NotesController {
    private final NotesService notesService;
    private final NotesModelAssembler assembler;

    public NotesController(NotesService notesService, NotesModelAssembler assembler) {
        this.notesService = notesService;
        this.assembler = assembler;
    }

    @GetMapping("/notes/all")
    @PreAuthorize("@securityRules.isAdmin(authentication.principal.getAuthorities())")
    public CollectionModel<EntityModel<NotesResponse>> getAllNotes() {
        var notesList = notesService.getAllNotes();

        List<EntityModel<NotesResponse>> notesEntityModel = notesList.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(notesEntityModel);
    }

    @GetMapping("/{username}/notes/all")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public CollectionModel<EntityModel<NotesResponse>> getNotesByUser(@PathVariable String username){
        var notesList = notesService.getNotesByUser(username);

        List<EntityModel<NotesResponse>> notesEntityModel = notesList.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(notesEntityModel);
    }

    @GetMapping("/{username}/notes/{notesID}")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public EntityModel<NotesResponse> getNotesByID(@PathVariable String username, @PathVariable Long notesID){
        try {
            var notesResponse = notesService.getNotesById(username, notesID);
            return EntityModel.of(notesResponse);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{username}/notes")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public ResponseEntity<EntityModel<NotesResponse>> createNotes(
            @PathVariable String username,
            @RequestBody NotesDTO notesDTO,
            @AuthenticationPrincipal UserDetails currentUser,
            UriComponentsBuilder uriComponentsBuilder){
        try{
            var notesResponse = notesService.createNotes(notesDTO, currentUser);

            var uri = uriComponentsBuilder
                    .path("/api/users/{username}/notes/{noteId}")
                    .buildAndExpand(username, notesResponse.id())
                    .toUri();

            return ResponseEntity.created(uri).build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{username}/notes/all")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public CollectionModel<EntityModel<NotesResponse>> createAllNotes(
            @PathVariable String username,
            @RequestBody ListNotesDTO listNotesDTO,
            @AuthenticationPrincipal UserDetails currentUser,
            UriComponentsBuilder uriComponentsBuilder){
        try {
            var notesResponseList = notesService.createAllNotes(listNotesDTO, currentUser);

            List<EntityModel<NotesResponse>> notesEntityModel = notesResponseList.stream()
                    .map(assembler::toModel)
                    .toList();

            return CollectionModel.of(notesEntityModel);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{username}/notes/{notesID}")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public ResponseEntity<EntityModel<Notes>> deleteNotes(@PathVariable String username, @PathVariable Long notesID){
        try{
            notesService.deleteNotes(notesID, username);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{username}/notes/{notesID}")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public ResponseEntity<EntityModel<NotesResponse>> updateNotes(
            @PathVariable String username, @PathVariable Long notesID, @RequestBody NotesDTO editedNotes){
        try {
            var updatedNotesResponse= notesService.updateNotes(notesID, editedNotes);
            return ResponseEntity.ok(assembler.toModel(updatedNotesResponse));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
