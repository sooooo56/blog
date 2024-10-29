package com.korea.blog.domain.main;

import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.note.repository.NoteRepository;
import com.korea.blog.domain.main.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final NoteService noteService;

    public void init(){
        List<Note> noteList = noteService.getList();

        if (getNoteList().isEmpty()){
            noteService.saveDefault();
        }
    }

    public List<Note> getNoteList() {

        return noteService.getList();
    }
}
