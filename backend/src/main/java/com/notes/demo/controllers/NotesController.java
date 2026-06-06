package com.notes.demo.controllers;

import com.notes.demo.assemblers.NotesModelAssembler;
import com.notes.demo.domain.notes.Notes;
import com.notes.demo.domain.notes.NotesDTO;
import com.notes.demo.domain.notes.NotesResponse;
import com.notes.demo.exception.custom.UserWithoutPermissionException;
import com.notes.demo.services.NotesService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping("/notes/all")
    @PreAuthorize("@securityRules.isAdmin(authentication.principal.getAuthorities())")
    public CollectionModel<EntityModel<NotesResponse>> getAllNotes() {
        List<NotesResponse> notesList = notesService.getAllNotes().stream().map(notes ->
                new NotesResponse(
                        notes.getIdNotes(),
                        notes.getTitle(),
                        notes.getBody(),
                        notes.getUser().getUsername()
                )).toList();

        List<EntityModel<NotesResponse>> notesEntityModel = notesList.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(notesEntityModel);
    }

    @GetMapping("/{username}/notes/all")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public CollectionModel<EntityModel<NotesResponse>> getNotesByUser(@PathVariable String username){
        List<NotesResponse> notesList = notesService.getAllNotesByUsername(username).stream().map(
                notes -> new NotesResponse(
                        notes.getIdNotes(),
                        notes.getTitle(),
                        notes.getBody(),
                        notes.getUser().getUsername()
                )
        ).toList();

        List<EntityModel<NotesResponse>> notesEntityModel = notesList.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(notesEntityModel);
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
            var updatedNotes = notesService.updateNotes(username, notesID, editedNotes);
            var updatedNotesResponse = new NotesResponse(
                    updatedNotes.getIdNotes(),
                    updatedNotes.getTitle(),
                    updatedNotes.getBody(),
                    updatedNotes.getUser().getUsername()
            );
            return ResponseEntity.ok(assembler.toModel(updatedNotesResponse));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
