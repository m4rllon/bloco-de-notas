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

    @GetMapping("/{username}")
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

    @PostMapping("/{username}")
    @PreAuthorize("@securityRules.canAccessRoute(#username, authentication.principal.getUsername(), authentication.principal.getAuthorities())")
    public ResponseEntity<EntityModel<NotesResponse>> createNotes(
            @PathVariable String username, @RequestBody NotesDTO notesDTO, @AuthenticationPrincipal UserDetails currentUser){
        try{
            var newNotes = new Notes(
                    notesDTO.getTitle(),
                    notesDTO.getBody(),
                    LocalDateTime.now(),
                    currentUser
            );
            var notes = notesService.createNotes(newNotes);
            var notesResponse = new NotesResponse(
                    notes.getIdNotes(),
                    notes.getTitle(),
                    notes.getBody(),
                    notes.getUser().getUsername()
            );
            return ResponseEntity.ok(assembler.toModel(notesResponse));
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

    @PutMapping("/{username}/{notesID}")
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
