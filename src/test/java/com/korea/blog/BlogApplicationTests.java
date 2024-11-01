package com.korea.blog;

import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.note.repository.NoteRepository;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import com.korea.blog.domain.main.notebook.repository.NotebookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private NotebookRepository notebookRepository;

	@Test
	@DisplayName("노트 테스트 데이터 생성")
	void t1() {
		Note note = Note.builder()
				.title("title1")
				.content("content1")
				.build();
		noteRepository.save(note);
	}

	@Test
	@DisplayName("노트 수정")
	@Rollback(value = false)
	void t2(){
		Note note = noteRepository.findById(9L).get();

		note.setTitle("제목수정");
		note.setContent("내용 수정");

		noteRepository.save(note); // DB에 반영해주는 메서드
	}

	@Test
	@DisplayName("노트 수정")
	@Rollback(value = false)
	@Transactional
	void t3(){
		Note note = noteRepository.findById(9L).get();

		note.setTitle("제목수정2");
		note.setContent("내용 수정2");
	}

	@Test
	@DisplayName("노트북과 노트 테스트 데이터 생성")
	void t4(){
		Notebook book = Notebook.builder()
				.name("test book")
				.build();

		notebookRepository.save(book);

		Note note = Note.builder()
				.title("test")
				.content("test content")
				.build();

//		book.getNoteList().add(note);
		// 엔터티기리 연결할 때는 외래키를 가진쪽이 관계의 주인
		// 관계의 주인이 DB를 반영

		note.setParent(book);

		noteRepository.save(note);
	}

}
