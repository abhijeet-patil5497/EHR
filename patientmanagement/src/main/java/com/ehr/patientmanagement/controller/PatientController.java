package com.ehr.patientmanagement.controller;


import com.ehr.patientmanagement.dto.PatientRequstDTO;
import com.ehr.patientmanagement.dto.PatientResponseDTO;
import com.ehr.patientmanagement.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatiens(){
        List<PatientResponseDTO> patientResponseDTO = patientService.getPatients();

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PostMapping
    public  ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequstDTO patientRequstDTO){

        PatientResponseDTO patientResponseDTO= patientService.createPatient(patientRequstDTO);

        return  ResponseEntity.ok().body(patientResponseDTO);

    }


}
