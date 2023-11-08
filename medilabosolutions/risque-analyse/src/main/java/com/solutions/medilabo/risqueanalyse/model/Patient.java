package com.solutions.medilabo.risqueanalyse.model;

import com.solutions.medilabo.risqueanalyse.model.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    private Long id;

    private String nom;

    private String prenom;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate dateNaissance;

    private Genre genre;

    private String adressePostale;

    private String numTelephone;
}
