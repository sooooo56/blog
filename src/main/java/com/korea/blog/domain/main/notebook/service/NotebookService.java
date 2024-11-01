package com.korea.blog.domain.main.notebook.service;

import com.korea.blog.domain.main.notebook.entity.Notebook;
import com.korea.blog.domain.main.notebook.repository.NotebookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {

    private final NotebookRepository notebookRepository;

    public List<Notebook> getList() {
        return notebookRepository.findAll();
    }

    public Notebook saveDefault() {
        Notebook notebook = Notebook.builder()
                .name("새노트북")
                .build();

        return notebookRepository.save(notebook);
    }

    @Transactional
    public Notebook saveSubNotebook(long parentId) {
        Notebook parentNotebook = getOne(parentId);
        Notebook subNotebook = saveDefault();
        parentNotebook.addSubNotebook(subNotebook);

        return parentNotebook;
    }

    public Notebook getOne(long bookId) {
        return notebookRepository.findById(bookId).orElseThrow();
    }

    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }
}