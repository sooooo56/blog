package com.korea.blog.domain.main.note.service;

import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> getList() {
        return noteRepository.findAll();
    }

    public Note saveDefault() {
        Note note = Note.builder()
                .title("New Title")
                .content("")
                .build();

        return noteRepository.save(note);
    }
}