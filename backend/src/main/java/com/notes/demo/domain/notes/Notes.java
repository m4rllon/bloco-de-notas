package com.notes.demo.domain.notes;

import com.notes.demo.domain.user.UserAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "username", nullable = false)
    private UserAccount user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Notes(String title, String body, LocalDateTime createdAt, UserDetails user) {
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.user = (UserAccount) user;
    }
}
