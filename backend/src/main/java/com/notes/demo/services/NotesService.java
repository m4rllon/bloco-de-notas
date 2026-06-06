package com.notes.demo.services;

import com.notes.demo.domain.notes.ListNotesDTO;
import com.notes.demo.domain.notes.Notes;
import com.notes.demo.domain.notes.NotesDTO;
import com.notes.demo.domain.notes.NotesResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface NotesService {
    List<NotesResponse> getAllNotes();

    List<Notes> getAllNotesByUsername(String username);

    List<NotesResponse> getNotesByUser(String username);

    NotesResponse getNotesById(String username, Long notesID);

    NotesResponse createNotes(NotesDTO notesDTO, UserDetails currentUser);

    List<NotesResponse> createAllNotes(ListNotesDTO listNotesDTO, UserDetails currentUser);

    void deleteNotes(Long notesID, String username);

    NotesResponse updateNotes(Long notesID, NotesDTO editedNotes);
}
