package com.notes.demo.domain.notes;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NotesDTO {
    private String title;
    private String body;
}
