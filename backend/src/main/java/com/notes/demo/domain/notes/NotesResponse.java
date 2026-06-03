package com.notes.demo.domain.notes;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotesResponse {
    private Long id;
    private String title;
    private String content;
    private String user;
}
