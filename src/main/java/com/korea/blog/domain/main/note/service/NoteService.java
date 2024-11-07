package com.korea.blog.domain.main.note.service;

import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.note.repository.NoteRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Note modify(long noteId, String title, String content) {

        if(title.trim().length() == 0) {
            title = "제목 없음";
        }

        Note note = getOne(noteId);
        note.setTitle(title);
        note.setContent(content);

        return note;
    }

    public void delete(long noteId) {
        noteRepository.deleteById(noteId);
    }

    public void deleteAll(List<Note> noteList) {
        noteRepository.deleteAll(noteList);
    }

    public List<Note> getSearchedList(String keyword) {
        return noteRepository.findByTitleContaining(keyword);
    }
}