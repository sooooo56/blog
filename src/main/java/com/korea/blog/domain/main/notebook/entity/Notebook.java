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

    @OneToMany(mappedBy = "parent")
    @Builder.Default
    List<Note> noteList = new ArrayList<>();

    public void addNote(Note note) {
        note.setParent(this);
        noteList.add(note);
    }
}