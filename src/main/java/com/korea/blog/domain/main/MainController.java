package com.korea.blog.domain.main;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostConstruct
    public void init() {
        mainService.init();
    }

    // 초기 화면 -> 첫번째 노트북의 첫번째 노트가 선택되도록 약속
    @GetMapping("/")
    public String main(Model model, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "false") String isModalOpen) {

        MainDataDto mainDataDto = mainService.getDefaultMainDataDto(keyword);
        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("keyword", keyword);
        model.addAttribute("isModalOpen", isModalOpen);

        return "main";
    }

    // 메인 화면
//    @GetMapping("/notes/{noteId}")
//    public String main(@PathVariable long noteId, Model model) {
//        List<Note> noteList = mainService.getNoteList();
//        Note selectedNote = mainService.getNote(noteId);
//
//        model.addAttribute("noteList", noteList);
//        model.addAttribute("selectedNote", selectedNote);
//
//        return "main";
//    }
}