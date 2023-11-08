package com.solutions.medilabo.risqueanalyse.service;

import com.solutions.medilabo.risqueanalyse.model.enums.Risque;

public interface RisqueService {
    Risque analyseRisquePatient(String authorizationHeader, Long idPatient);
}
