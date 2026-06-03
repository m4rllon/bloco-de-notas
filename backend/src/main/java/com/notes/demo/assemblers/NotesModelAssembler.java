package com.notes.demo.assemblers;

import com.notes.demo.domain.notes.NotesResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class NotesModelAssembler implements RepresentationModelAssembler<NotesResponse, EntityModel<NotesResponse>> {
    @Override
    public EntityModel<NotesResponse> toModel(NotesResponse notes) {
        try{
            EntityModel<NotesResponse> notesEntityModel = EntityModel.of(
                    notes
            );

            return notesEntityModel;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
