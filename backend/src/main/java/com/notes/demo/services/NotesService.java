package com.notes.demo.services;

import com.notes.demo.dtos.NotesDTO;
import com.notes.demo.models.Notes;

import java.util.List;

public interface NotesService {
//    Notes createNotes(NotesDTO newNotes);

    List<Notes> getAllNotes();
}
