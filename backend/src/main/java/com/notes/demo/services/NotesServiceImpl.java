package com.notes.demo.services;

import com.notes.demo.domain.notes.ListNotesDTO;
import com.notes.demo.domain.notes.Notes;
import com.notes.demo.domain.notes.NotesDTO;
import com.notes.demo.domain.notes.NotesResponse;
import com.notes.demo.exception.custom.NotesNotExistsException;
import com.notes.demo.exception.custom.UserWithoutPermissionException;
import com.notes.demo.repositories.NotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class NotesServiceImpl implements NotesService{
    @Autowired
    private NotesRepository notesRepository;

    @Override
    public List<NotesResponse> getAllNotes(){
        try{
            List<NotesResponse> notesList = notesRepository.findAll().stream().map(notes ->
                    new NotesResponse(
                            notes.getIdNotes(),
                            notes.getTitle(),
                            notes.getBody(),
                            notes.getCreatedAt(),
                            notes.getUser().getUsername())).toList();
            return notesList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notes> getAllNotesByUsername(String username){
        try{
            return notesRepository.findByUserUsername(username);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NotesResponse> getNotesByUser(String username){
        try {
            List<NotesResponse> notesList = this.getAllNotesByUsername(username).stream().map(
                    notes -> new NotesResponse(
                            notes.getIdNotes(),
                            notes.getTitle(),
                            notes.getBody(),
                            notes.getCreatedAt(),
                            notes.getUser().getUsername()
                    )
            ).toList();

            return notesList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NotesResponse getNotesById(String username, Long notesID) {
        try{
            var notes = notesRepository.findById(notesID).orElse(null);
            if(notes == null)
                throw new NotesNotExistsException("Notes not found.");
            return new NotesResponse(
                    notes.getIdNotes(),
                    notes.getTitle(),
                    notes.getBody(),
                    notes.getCreatedAt(),
                    username
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NotesResponse createNotes(NotesDTO notesDTO, UserDetails currentUser) {
        try{
            var newNotes = new Notes(
                    notesDTO.title(),
                    notesDTO.body(),
                    timestampConverter(notesDTO.timestamp()),
                    currentUser
            );
            var notes = notesRepository.save(newNotes);
            return new NotesResponse(
                    notes.getIdNotes(),
                    notes.getTitle(),
                    notes.getBody(),
                    notes.getCreatedAt(),
                    notes.getUser().getUsername()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NotesResponse> createAllNotes(ListNotesDTO listNotesDTO, UserDetails currentUser) {
        try {
            List<Notes> notesList = parseNotesString(listNotesDTO.listNotes(), currentUser);

            notesList.forEach(notes -> notesRepository.save(notes));

            List<NotesResponse> notesResponseList = notesList.stream().map(
                    notes -> new NotesResponse(
                            notes.getIdNotes(),
                            notes.getTitle(),
                            notes.getBody(),
                            notes.getCreatedAt(),
                            notes.getUser().getUsername()
                    )
            ).toList();

            return  notesResponseList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteNotes(Long notesID, String username) {
        try{
            Notes notes = notesRepository.findById(notesID).orElse(null);
            if(notes == null) throw new NotesNotExistsException("Notes not found.");
            if(notes.getUser().getUsername().equals(username)){
                notesRepository.deleteById(notesID);
            }
            else throw new UserWithoutPermissionException();
        } catch (NotesNotExistsException e) {
            throw new NotesNotExistsException(e.getMessage());
        } catch (UserWithoutPermissionException e) {
            throw new UserWithoutPermissionException();
        }
    }

    @Override
    public NotesResponse updateNotes(Long notesID, NotesDTO editedNotes){
        try {
            Notes notes = notesRepository.findById(notesID).orElse(null);

            if(notes == null) throw new NotesNotExistsException("Notes not exists.");

            notes.setBody(editedNotes.body());
            notes.setTitle(editedNotes.title());
            notes.setCreatedAt(timestampConverter(editedNotes.timestamp()));

            notesRepository.save(notes);

            return new NotesResponse(
                    notes.getIdNotes(),
                    notes.getTitle(),
                    notes.getBody(),
                    notes.getCreatedAt(),
                    notes.getUser().getUsername()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Notes> parseNotesString(String jsonListNotes, UserDetails currentUser){
        List<Notes> notesList = new ArrayList<>();

        String cleanJson = jsonListNotes.replace("\\\"", "\"");

        String regex = "\"title\":\"([^\"]+)\",\"body\":\"([^\"]+)\",\"timestamp\":\"([^\"]+)\"";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cleanJson);

        while (matcher.find()){
            String title = matcher.group(1);
            String body = matcher.group(2);
            String createdAt = matcher.group(3);

            Notes notes = new Notes(title, body, timestampConverter(createdAt), currentUser);

            notesList.add(notes);
        }

        return  notesList;
    }

    private static LocalDateTime timestampConverter(String timestamp){
        Instant instant = Instant.parse(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
    }
}
