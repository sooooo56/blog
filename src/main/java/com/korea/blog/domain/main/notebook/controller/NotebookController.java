package com.korea.blog.domain.main.notebook.controller;

import com.korea.blog.domain.main.MainDataDto;
import com.korea.blog.domain.main.MainService;
import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import com.korea.blog.domain.main.notebook.service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{bookId}/write")
    public String writeSubBook(@PathVariable long bookId) {

        notebookService.checkSubNotebook(bookId);

        Notebook subNotebook = mainService.saveSubNotebook(bookId);
        return "redirect:/books/%d/notes/%d".formatted(subNotebook.getId(), subNotebook.getNoteList().getFirst().getId());
    }

    @PostMapping("/{bookId}/modify")
    public String modifyBook(@PathVariable long bookId, String name, long selectedNoteId) {
        notebookService.modify(bookId, name);
        return "redirect:/books/%d/notes/%d".formatted(bookId, selectedNoteId);
    }

    @PostMapping("/{bookId}/delete")
    public String deleteBook(@PathVariable long bookId) {
//        mainService.deleteNotebook(bookId);
        notebookService.delete(bookId);
        return "redirect:/books/%d".formatted(notebookService.getList().getFirst().getId());
    }

    @PostMapping("/{bookId}/notes/write")
    public String writeNote(@PathVariable long bookId) {
        Note note = mainService.saveDefaultNote(bookId);
        return "redirect:/books/%d/notes/%d".formatted(bookId, note.getId());
    }

    @GetMapping("/{bookId}/notes/{noteId}")
    public String selectNote(@PathVariable long bookId, @RequestParam(defaultValue = "") String keyword, @PathVariable long noteId, Model model) {

        MainDataDto mainDataDto = mainService.getMainDataDto(bookId, noteId, keyword);
        model.addAttribute("mainDataDto", mainDataDto);

        return "main";
    }

    @GetMapping("/{bookId}")
    public String select(@PathVariable long bookId, @RequestParam(defaultValue = "") String keyword, String isModalOpen, Model model) {

        MainDataDto mainDataDto = mainService.getDefaulNoteMainDataDto(bookId, keyword);
        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("keyword", keyword);
        model.addAttribute("isModalOpen", isModalOpen);

        return "main";
    }
}