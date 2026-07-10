package com.micro.patient_service.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.patient_service.model.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient,UUID> {

    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailAndIdNot(String email,UUID id);
    
}