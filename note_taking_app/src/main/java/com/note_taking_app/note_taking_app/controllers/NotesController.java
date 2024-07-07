package com.note_taking_app.note_taking_app.controllers;

import com.note_taking_app.note_taking_app.models.Note;
import com.note_taking_app.note_taking_app.repository.NoteRepo;
import com.note_taking_app.note_taking_app.utilities.MarkdownConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/notes")
public class NotesController {

    private final NoteRepo noteRepository;

    @Autowired
    public NotesController(NoteRepo noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PostMapping("/save_note")
    public ResponseEntity<Note> saveNote(@RequestBody Map<String, String> payload) {
        String markdown = payload.get("markdown");
        String title = payload.get("title");
        String html = markdownToHtml(markdown);
        Note note = new Note();
        note.setMarkdown(markdown);
        note.setHtml(html);
        note.setTitle(title);
        Note savedNote = noteRepository.save(note);
        try {
            if (savedNote != null && savedNote.getNote_id() != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(savedNote);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(savedNote);
        }
        
    }

    @GetMapping("/render_note")
    public ResponseEntity<String> renderNote(@RequestParam("note_id") String noteId) {
        Optional<Note> note = noteRepository.findById(noteId);
        System.out.printf(noteId);
        return note.map(value -> ResponseEntity.ok(value.getHtml())).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found"));
    }

    @GetMapping("/render_note_title")
    public ResponseEntity<String> renderNoteByTitle(@RequestParam("title") String title) {
        Optional<Note> note = noteRepository.findByTitle(title);
        System.out.println(title);
        return note.map(value -> ResponseEntity.ok(value.getHtml()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found"));
    }


    private String markdownToHtml(String markdown) {
        return MarkdownConverter.markdownToHtml(markdown).toString();
    }
}
