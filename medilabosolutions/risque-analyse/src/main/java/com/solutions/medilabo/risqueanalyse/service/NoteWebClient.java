package com.solutions.medilabo.risqueanalyse.service;

import com.solutions.medilabo.risqueanalyse.model.Note;

import java.util.List;

public interface NoteWebClient {

    List<Note> getAllNotesByPatientId(Long id);
}
