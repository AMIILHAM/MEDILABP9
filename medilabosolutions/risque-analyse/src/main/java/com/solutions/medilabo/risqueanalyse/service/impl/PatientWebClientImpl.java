package com.solutions.medilabo.risqueanalyse.service.impl;

import com.solutions.medilabo.risqueanalyse.model.Patient;
import com.solutions.medilabo.risqueanalyse.service.PatientWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PatientWebClientImpl implements PatientWebClient {

    private final RestTemplate restTemplate;

    @Override
    public Patient getPatientById(String authorizationHeader, Long id) {

        String url = "http://localhost:8081/patients/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Patient> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Patient.class);

        return responseEntity.getBody();
    }
}
