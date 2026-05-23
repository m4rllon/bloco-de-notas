package com.notes.demo.repositories;

import com.notes.demo.domain.notes.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Long> {
}
