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
        Notebook subNotebook = saveDefault(); // 서브 노트북

        parentNotebook.addSubNotebook(subNotebook);

        return subNotebook;
    }

    public Notebook getOne(long bookId) {
        return notebookRepository.findById(bookId).orElseThrow();
    }

    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    public void checkSubNotebook(long bookId) {
        Notebook notebook = getOne(bookId);

        if(notebook.getParent() != null) {
            throw new RuntimeException("아직은 서브 노트북에 서브 노트북을 추가할 수 없습니다. 추후 업데이트 예정");
        }

    }

    public void delete(long bookId) {
        notebookRepository.deleteById(bookId);
    }

    public void delete(Notebook subNotebook) {
        notebookRepository.delete(subNotebook);
    }
}