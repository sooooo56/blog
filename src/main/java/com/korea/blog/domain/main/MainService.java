package com.korea.blog.domain.main;

import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.note.service.NoteService;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import com.korea.blog.domain.main.notebook.service.NotebookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final NoteService noteService;
    private final NotebookService notebookService;

    @Transactional
    public void init() {
        List<Notebook> notebookList = notebookService.getList();

        if(notebookList.isEmpty()) {
            saveDefaultNotebook();
        }
    }

    @Transactional
    public Notebook saveDefaultNotebook() {
        Notebook notebook = notebookService.saveDefault();
        Note note = noteService.saveDefault();
        notebook.addNote(note);

        return notebook;
    }

    public Note saveDefaultNote(long bookId) {
        Notebook notebook = notebookService.getOne(bookId);
        Note note = noteService.saveDefault();

        notebook.addNote(note);
        notebook = notebookService.save(notebook);

        return note;
    }

    public List<Note> getNoteList() {
        return noteService.getList();
    }

    public Note getNote(long noteId) {
        return noteService.getOne(noteId);
    }

    public List<Notebook> getNoteBookList() {
        return notebookService.getList();
    }
}