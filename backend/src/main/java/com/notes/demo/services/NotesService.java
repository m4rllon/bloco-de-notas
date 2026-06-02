package com.notes.demo.services;

import com.notes.demo.domain.notes.Notes;
import com.notes.demo.dtos.NotesDTO;

import java.util.List;

public interface NotesService {
    List<Notes> getAllNotes();

    List<Notes> getAllNotesByUsername(String username);

    Notes createNotes(Notes newNotes);

    void deleteNotes(Long notesID, String username);

    Notes updateNotes(String username, Long notesID, NotesDTO editedNotes);
}
