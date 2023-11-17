package com.solutions.medilabo.gestionnotes.web;


import com.solutions.medilabo.gestionnotes.model.Note;
import com.solutions.medilabo.gestionnotes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notes/")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Authorization", "Origin"}, exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
public class NoteController {

    private final NoteRepository noteRepository;


    @PostMapping("/save")
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        log.info("======== Request for adding note {} ========", note.getId());
        return ResponseEntity.ok(this.noteRepository.save(note));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Note>> getAllNoteByPatientId(@PathVariable Long id){
        log.info("======== Request for Getting All notes By PatientID {} ========", id);
        return ResponseEntity.ok(this.noteRepository.findAllByPatientId(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable Long id){
        log.info("======== Request for deleting note {} ========", id);
        this.noteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
