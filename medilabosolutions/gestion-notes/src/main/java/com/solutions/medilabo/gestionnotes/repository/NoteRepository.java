package com.solutions.medilabo.gestionnotes.repository;

import com.solutions.medilabo.gestionnotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, Long> {

    @Query("{patientName:?0}")
    List<Note> findAllByPatientName(String name);

    @Query("{patientId:?0}")
    List<Note> findAllByPatientId(Long patientId);
    @Query("{id:?0}")
    List<Note> findAllById(Long id);

}
