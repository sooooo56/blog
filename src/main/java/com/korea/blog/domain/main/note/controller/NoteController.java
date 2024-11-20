package com.korea.blog.domain.main.note.controller;

import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.note.service.NoteService;
import com.korea.blog.global.dto.UrlParamManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @GetMapping("")
    public String list() {
        return "main";
    }

    @PostMapping("/delete/{noteId}")
    public String delete(@PathVariable long noteId, UrlParamManager urlParamManager) {
        noteService.delete(noteId);
        return urlParamManager.getRedirectUrl("/");
    }

    @PostMapping("/modify/{noteId}")
    public String modify(@PathVariable long noteId, String title, String content, UrlParamManager urlParamManager) {
        Note note = noteService.modify(noteId, title, content);

        return urlParamManager.getRedirectUrl("/books/%d/notes/%d".formatted(note.getParent().getId(), noteId));
    }

    @GetMapping("/{noteId}")
    public String select(@PathVariable long noteId, Model model) {

        List<Note> noteList = noteService.getList();
        Note selectedNote = noteService.getOne(noteId);

        model.addAttribute("noteList", noteList);
        model.addAttribute("selectedNote", selectedNote);

        return "main";
    }
}