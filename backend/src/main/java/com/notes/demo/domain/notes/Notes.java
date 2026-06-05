package com.notes.demo.domain.notes;

import com.notes.demo.domain.user.UserAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return idNotes == notes.idNotes && Objects.equals(title, notes.title) && Objects.equals(body, notes.body) && Objects.equals(user, notes.user) && Objects.equals(createdAt, notes.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNotes, title, body, user, createdAt);
    }

    @Override
    public String toString() {
        return "Notes{" +
                "idNotes=" + idNotes +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }
}
