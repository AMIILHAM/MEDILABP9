package com.solutions.medilabo.risqueanalyse.service;

import com.solutions.medilabo.risqueanalyse.model.Patient;

public interface PatientWebClient {

    Patient getPatientById(String authorizationHeader,Long id);
}
