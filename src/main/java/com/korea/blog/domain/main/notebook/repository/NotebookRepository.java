package com.korea.blog.domain.main.notebook.repository;

import com.korea.blog.domain.main.notebook.entity.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {

    List<Notebook> findByNameContaining(String keyword);

}