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

//    public void deleteNotebook(long bookId) {
//        Notebook deleteTarget = notebookService.getOne(bookId);
//        List<Notebook> subNotebookList = deleteTarget.getSubNotebookList();
//
//        for(Notebook subNotebook : subNotebookList) {
//            List<Note> noteList = subNotebook.getNoteList();
//            noteService.deleteAll(noteList);
//            notebookService.delete(subNotebook);
//        }
//
//        noteService.deleteAll(deleteTarget.getNoteList());
//        notebookService.delete(deleteTarget);
//    }

    @Transactional
    public Notebook saveSubNotebook(long parentId) {
        Notebook subNotebook = notebookService.saveSubNotebook(parentId);
        Note note = noteService.saveDefault();

        // 서브 노트북의 기본 노트 생성
        subNotebook.addNote(note);

        return subNotebook;
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

    public List<Notebook> getSearchedNotebookList(String keyword) {
        return notebookService.getSearchedList(keyword);
    }

    public List<Note> getSearchedNoteList(String keyword) {
        return noteService.getSearchedList(keyword);
    }

    public MainDataDto getMainDataDto(long bookId, long noteId, String keyword,String sortTarget) {
        List<Notebook> notebookList = getNoteBookList();
        Notebook selectedNotebook = notebookService.getOne(bookId);

        List<Note> noteList;

        if (sortTarget.equals("title")){
            noteList = noteService.getListOrderByTitleAsc(selectedNotebook.getId());
        } else {
            noteList = noteService.getListOrderByIdDesc(selectedNotebook.getId());
        }

        Note selectedNote = noteService.getOne(noteId);

        List<Notebook> searchedNotebookList = getSearchedNotebookList(keyword);
        List<Note> searchedNoteList = getSearchedNoteList(keyword);

        return MainDataDto.builder()
                .notebookList(notebookList)
                .selectedNotebook(selectedNotebook)
                .noteList(noteList)
                .selectedNote(selectedNote)
                .searchedNotebookList(searchedNotebookList)
                .searchedNoteList(searchedNoteList)
                .build();
    }

    public MainDataDto getDefaulNoteMainDataDto(long bookId, String keyword,String sortTarget) {
        Notebook notebook = notebookService.getOne(bookId);
        long noteId = notebook.getNoteList().getFirst().getId();
        return getMainDataDto(bookId, noteId, keyword,sortTarget);
    }

    public MainDataDto getDefaultMainDataDto(String keyword,String sortTarget) {
        Notebook firstNotebook = notebookService.getList().getFirst();
        return getDefaulNoteMainDataDto(firstNotebook.getId(), keyword,sortTarget);
    }
}