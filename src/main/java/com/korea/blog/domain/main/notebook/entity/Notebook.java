package com.korea.blog.domain.main.notebook.entity;

import com.korea.blog.domain.main.note.entity.Note;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Note> noteList = new ArrayList<>();

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Notebook> subNotebookList = new ArrayList<>();

    @ManyToOne
    private Notebook parent;

    public void addNote(Note note) {
        note.setParent(this);
        noteList.add(note);
    }

    public void addSubNotebook(Notebook subNotebook) {
        subNotebook.setParent(this);
        subNotebookList.add(subNotebook);
    }
}