package com.notes.demo.services;

import com.notes.demo.domain.notes.Notes;
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

//    @Override
//    public Notes createNotes(NotesDTO notesDTO){
//        try{
//            Notes newNotes = new Notes(
//                    notesDTO.getTitle(),
//                    notesDTO.getBody(),
//                    1,
//                    LocalDateTime.now()
//            );
//
//            return notesRepository.save(newNotes);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public List<Notes> getAllNotes(){
        try{
            return notesRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
