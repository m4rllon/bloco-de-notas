package com.notes.demo.domain.notes;

import com.notes.demo.domain.user.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notes", nullable = false)
    private long idNotes;

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "body", nullable = true)
    private String body;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private UserAccount user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Notes(String title, String body, UserAccount user, LocalDateTime createdAt) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Notes(){}
}
