package com.solutions.medilabo.risqueanalyse.service.impl;

import com.solutions.medilabo.risqueanalyse.model.Note;
import com.solutions.medilabo.risqueanalyse.model.Patient;
import com.solutions.medilabo.risqueanalyse.model.enums.Genre;
import com.solutions.medilabo.risqueanalyse.model.enums.Risque;
import com.solutions.medilabo.risqueanalyse.service.NoteWebClient;
import com.solutions.medilabo.risqueanalyse.service.PatientWebClient;
import com.solutions.medilabo.risqueanalyse.service.RisqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RisqueServiceImpl implements RisqueService {

    private final NoteWebClient noteWebClient;
    private final PatientWebClient patientWebClient;

    @Override
    public Risque analyseRisquePatient(String authorizationHeader, Long idPatient) {

        Patient patient = this.patientWebClient.getPatientById(authorizationHeader,idPatient);
        List<Note> notes = this.noteWebClient.getAllNotesByPatientId(idPatient);

        if(notes.stream().noneMatch(note -> hasOneOfKeywords(note.getNote()))){
            return Risque.NONE;
        }else if(this.getPatientAge(patient) > 30 && (this.countKeywordsInAllNotes(notes) >= 2 && this.countKeywordsInAllNotes(notes) <= 5)){
            return Risque.BORDERLINE;
        } else if (patient.getGenre().equals(Genre.MALE) && this.getPatientAge(patient) < 30 ) {
            if(this.countKeywordsInAllNotes(notes) >= 3){
                return Risque.IN_DANGER;
            }else if(this.countKeywordsInAllNotes(notes) >= 5) {
                return Risque.EARLY_ONSET;
            }
        } else if (patient.getGenre().equals(Genre.FEMALE) && this.getPatientAge(patient) < 30) {
            if(this.countKeywordsInAllNotes(notes) >= 4){
                return Risque.IN_DANGER;
            }else if(this.countKeywordsInAllNotes(notes) >= 7) {
                return Risque.EARLY_ONSET;
            }
        }else if(this.getPatientAge(patient) > 30 && this.countKeywordsInAllNotes(notes) >= 8){
            return Risque.EARLY_ONSET;
        }
        return Risque.NONE;
    }


    private List<String> declencheurs() {
        return List.of("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Fumeuse", "Anormal",
                "Cholestérol","Vertiges","Rechute","Réaction","Anticorps");
    }

    private boolean hasOneOfKeywords(String note) {
        return this.declencheurs().stream().anyMatch(keyword -> note.toLowerCase().contains(keyword.toLowerCase()));
    }

    private int countKeywordsInAllNotes(List<Note> notes) {
        return notes.stream()
                .mapToInt(note -> (int) declencheurs().stream()
                        .filter(keyword -> note.getNote().toLowerCase().contains(keyword.toLowerCase()))
                        .count())
                .sum();
    }


    private int getPatientAge(Patient patient) {
        LocalDate currentDate = LocalDate.now();
        if (patient.getDateNaissance() != null) {
            return Period.between(patient.getDateNaissance(), currentDate).getYears();
        } else {
            return 0;
        }
    }
}
