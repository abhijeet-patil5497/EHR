package com.ehr.patientmanagement.repository;

import com.ehr.patientmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    boolean existsByEmailId(String email);

}
