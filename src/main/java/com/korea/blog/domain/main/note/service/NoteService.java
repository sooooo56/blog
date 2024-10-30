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
                .title("new title..")
                .content("")
                .build();

        return noteRepository.save(note);
    }

    public Note getOne(long noteId) {
        return noteRepository.findById(noteId).orElseThrow();
    }

    public void modify(long noteId, String title, String content) {

        if(title.trim().length() == 0) {
            title = "제목 없음";
        }

        Note note = getOne(noteId);
        note.setTitle(title);
        note.setContent(content);

        noteRepository.save(note);
    }

    public void delete(long noteId) {
        noteRepository.deleteById(noteId);
    }
}