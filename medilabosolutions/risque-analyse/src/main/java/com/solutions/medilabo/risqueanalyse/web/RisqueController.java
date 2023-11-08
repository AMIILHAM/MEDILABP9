package com.solutions.medilabo.risqueanalyse.web;

import com.solutions.medilabo.risqueanalyse.model.enums.Risque;
import com.solutions.medilabo.risqueanalyse.service.RisqueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/risque")
public class RisqueController {

    private final RisqueService risqueService;


    @GetMapping("/analyse/{id}")
    public ResponseEntity<Risque> getPatientRisque(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long id){
        log.info("=========== Request for Analyse Risque of Patient {}", id);
        return ResponseEntity.ok(this.risqueService.analyseRisquePatient(authorizationHeader,id));
    }
}
