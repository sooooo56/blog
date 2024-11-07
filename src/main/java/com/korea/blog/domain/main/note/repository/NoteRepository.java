package com.korea.blog.domain.main.note.repository;

import com.korea.blog.domain.main.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByTitleContaining(String keyword);
}