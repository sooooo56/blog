package com.korea.blog.domain.main.notebook.controller;

import com.korea.blog.domain.main.MainService;
import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import com.korea.blog.domain.main.notebook.service.NotebookService;
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
@RequestMapping("/books")
public class NotebookController {

    private final NotebookService notebookService; // 노트북 처리 전문
    private final MainService mainService; // 노트북 + 노트 혼합 작업 전문

    @PostMapping("/write")
    public String writeBook() {
//        Notebook notebook = notebookService.saveDefault();

        Notebook notebook = mainService.saveDefaultNotebook();
        return "redirect:/books/%d".formatted(notebook.getId());
    }

    @PostMapping("/{bookId}/notes/write")
    public String writeNote(@PathVariable long bookId) {
        Note note = mainService.saveDefaultNote(bookId);
        return "redirect:/books/%d/notes/%d".formatted(bookId, note.getId());
    }

    @GetMapping("/{bookId}/notes/{noteId}")
    public String selectNote(@PathVariable long bookId, @PathVariable long noteId, Model model) {

        List<Notebook> notebookList = notebookService.getList();
        Notebook selectedNotebook = notebookService.getOne(bookId);
        Note selectedNote = mainService.getNote(noteId);
        List<Note> noteList = selectedNotebook.getNoteList();

        model.addAttribute("notebookList", notebookList);
        model.addAttribute("noteList", noteList);
        model.addAttribute("selectedNotebook", selectedNotebook);
        model.addAttribute("selectedNote", selectedNote);

        return "main";
    }

//    @GetMapping("")
//    public String list(Model model) {
//
//        List<Notebook> notebookList = notebookService.getList();
//        List<Note> noteList = mainService.getNoteList();
//        Note selectedNote = noteList.getFirst();
//
//        model.addAttribute("notebookList", notebookList);
//        model.addAttribute("noteList", noteList);
//        model.addAttribute("selectedNote", selectedNote);
//
//        return "main";
//    }

    @GetMapping("/{bookId}")
    public String select(@PathVariable long bookId, Model model) {

        List<Notebook> notebookList = notebookService.getList();
        Notebook selectedNotebook = notebookService.getOne(bookId);
        List<Note> noteList = selectedNotebook.getNoteList();
        Note selectedNote = noteList.getFirst();

        model.addAttribute("notebookList", notebookList);
        model.addAttribute("noteList", noteList);
        model.addAttribute("selectedNotebook", selectedNotebook);
        model.addAttribute("selectedNote", selectedNote);

        return "main";
    }
}