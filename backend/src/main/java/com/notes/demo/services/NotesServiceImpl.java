package com.notes.demo.services;

import com.notes.demo.domain.notes.Notes;
import com.notes.demo.domain.notes.NotesDTO;
import com.notes.demo.repositories.NotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class NotesServiceImpl implements NotesService{
    @Autowired
    private NotesRepository notesRepository;

    @Override
    public List<Notes> getAllNotes(){
        try{
            return notesRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notes> getAllNotesByUsername(String username){
        try{
            var notesList = notesRepository.findAll();
            return notesList.stream().filter(note -> note.getUser().getUsername().equals(username)).toList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Notes createNotes(Notes newNotes) {
        try{
            return notesRepository.save(newNotes);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteNotes(Long notesID, String username) {
        try{
            Notes notes = notesRepository.findById(notesID).orElseThrow(RuntimeException::new);
            if(notes.getUser().getUsername().equals(username)){
                notesRepository.deleteById(notesID);

            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Notes updateNotes(String username, Long notesID, NotesDTO editedNotes){
        try {
            Notes notes = notesRepository.findById(notesID).orElseThrow(RuntimeException::new);
            notes.setBody(editedNotes.body());
            notes.setTitle(editedNotes.title());
            return notesRepository.save(notes);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
