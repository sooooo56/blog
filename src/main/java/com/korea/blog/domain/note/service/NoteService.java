package com.korea.blog.domain.note.service;

import com.korea.blog.domain.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
}