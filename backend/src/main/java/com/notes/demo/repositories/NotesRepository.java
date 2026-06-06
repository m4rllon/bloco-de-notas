package com.notes.demo.repositories;

import com.notes.demo.domain.notes.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Notes, Long> {

    public List<Notes> findByUserUsername(String username);
}
