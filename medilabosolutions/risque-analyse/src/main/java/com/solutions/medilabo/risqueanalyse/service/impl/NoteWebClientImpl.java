package com.solutions.medilabo.risqueanalyse.service.impl;

import com.solutions.medilabo.risqueanalyse.model.Note;
import com.solutions.medilabo.risqueanalyse.service.NoteWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteWebClientImpl implements NoteWebClient {

    private final RestTemplate restTemplate;

    @Override
    public List<Note> getAllNotesByPatientId(Long id) {

        String url = "http://localhost:8082/notes/all/" + id;

        ParameterizedTypeReference<List<Note>> responseType = new ParameterizedTypeReference<>(){};
        ResponseEntity<List<Note>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

        return responseEntity.getBody();

    }



}
