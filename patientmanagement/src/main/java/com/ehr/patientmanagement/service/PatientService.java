package com.ehr.patientmanagement.service;

import com.ehr.patientmanagement.dto.PatientRequstDTO;
import com.ehr.patientmanagement.dto.PatientResponseDTO;
import com.ehr.patientmanagement.entity.Patient;
import com.ehr.patientmanagement.exception.EmailAlreadyExistsException;
import com.ehr.patientmanagement.mapper.PatientMapper;
import com.ehr.patientmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {


    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients=patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequstDTO patientRequstDTO){

        if(patientRepository.existsByEmailId(patientRequstDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email alreaddy exists");
        }

        Patient newPatient = new Patient();
             newPatient=PatientMapper.toModel(patientRequstDTO);
             newPatient.setId(UUID.randomUUID().toString());

              newPatient =  patientRepository.save(newPatient);

        return  PatientMapper.toDTO(newPatient);
    }



}
