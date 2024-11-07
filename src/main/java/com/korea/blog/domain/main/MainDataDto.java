package com.korea.blog.domain.main;

import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MainDataDto {
    private List<Notebook> notebookList;
    private Notebook selectedNotebook;
    private List<Note> noteList;
    private Note selectedNote;
    private List<Notebook> searchedNotebookList;
    private List<Note> searchedNoteList;
}