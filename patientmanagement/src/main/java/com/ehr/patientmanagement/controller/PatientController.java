package com.ehr.patientmanagement.controller;


import com.ehr.patientmanagement.dto.PatientRequstDTO;
import com.ehr.patientmanagement.dto.PatientResponseDTO;
import com.ehr.patientmanagement.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatiens(){
        List<PatientResponseDTO> patientResponseDTO = patientService.getPatients();

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public  ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequstDTO patientRequstDTO){

        PatientResponseDTO patientResponseDTO= patientService.createPatient(patientRequstDTO);

        return  ResponseEntity.ok().body(patientResponseDTO);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Patients")
    public ResponseEntity<PatientResponseDTO> updatePatient (@PathVariable String id, @RequestBody PatientRequstDTO patientRequstDTO){

        PatientResponseDTO patientResponseDTO= patientService.updatePatient(id, patientRequstDTO);

        return  ResponseEntity.ok().body(patientResponseDTO);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public  ResponseEntity<Void> deletePatient(@PathVariable String id){

        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
    }


}
