package com.solutions.medilabo.risqueanalyse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Note {

    private String id;
    private Long patientId;
    private String patientName;
    private String note;
    private Date dateNote;
}
