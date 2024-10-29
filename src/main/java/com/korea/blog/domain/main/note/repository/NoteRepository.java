package com.korea.blog.domain.main.note.repository;

import com.korea.blog.domain.main.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}