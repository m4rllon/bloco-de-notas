package com.notes.demo.services;

import com.notes.demo.domain.notes.Notes;
import com.notes.demo.domain.notes.NotesDTO;
import com.notes.demo.domain.notes.NotesResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface NotesService {
    List<Notes> getAllNotes();

    List<Notes> getAllNotesByUsername(String username);

    NotesResponse getNotesById(String username, Long notesID);

    NotesResponse createNotes(NotesDTO notesDTO, UserDetails currentUser);

    void deleteNotes(Long notesID, String username);

    Notes updateNotes(String username, Long notesID, NotesDTO editedNotes);
}
