package com.notes.demo.domain.notes;

import java.time.LocalDateTime;

public record NotesResponse (Long id, String title, String content, LocalDateTime createdAt, String user){}
