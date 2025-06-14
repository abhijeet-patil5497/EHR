package com.ehr.patientmanagement.mapper;

import com.ehr.patientmanagement.dto.PatientRequstDTO;
import com.ehr.patientmanagement.dto.PatientResponseDTO;
import com.ehr.patientmanagement.entity.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();

        patientResponseDTO.setId(patient.getId());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientResponseDTO;
    }

    public static Patient toModel(PatientRequstDTO patientRequstDTO){
        Patient patient= new Patient();
        patient.setName(patientRequstDTO.getName());
        patient.setAddress(patientRequstDTO.getAddress());
        patient.setEmail(patientRequstDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequstDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequstDTO.getRegisteredDate()));

        return patient;
    }
}
