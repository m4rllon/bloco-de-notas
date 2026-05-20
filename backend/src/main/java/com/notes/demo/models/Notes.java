package com.notes.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idNotes;

    private String title;

    private String body;
}
