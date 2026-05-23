package com.notes.demo.services;

import com.notes.demo.domain.notes.Notes;

import java.util.List;

public interface NotesService {
//    Notes createNotes(NotesDTO newNotes);

    List<Notes> getAllNotes();
}
