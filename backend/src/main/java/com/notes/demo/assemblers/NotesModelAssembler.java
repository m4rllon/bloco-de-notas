package com.notes.demo.assemblers;

import com.notes.demo.domain.Notes;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class NotesModelAssembler implements RepresentationModelAssembler<Notes, EntityModel<Notes>> {
    @Override
    public EntityModel<Notes> toModel(Notes notes) {
        try{
            EntityModel<Notes> notesEntityModel = EntityModel.of(
                    notes
            );

            return notesEntityModel;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
