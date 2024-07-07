package com.note_taking_app.note_taking_app.repository;

import com.note_taking_app.note_taking_app.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepo extends MongoRepository<Note, String> {
    Optional<Note> findByTitle(String title);
}
