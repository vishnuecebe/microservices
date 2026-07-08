package com.micro.patient_service.mapper;

import java.time.LocalDate;

import com.micro.patient_service.dtos.PatientRequestDTO;
import com.micro.patient_service.dtos.PatientResponseDTO;
import com.micro.patient_service.model.Patient;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setRegisterdDate(patient.getRegisteredDate().toString());
        return patientDTO;
    }

    public static Patient toPatient(PatientRequestDTO patientResponseDTO){
        Patient patient = new Patient();
        patient.setName(patientResponseDTO.getName());
        patient.setAddress(patientResponseDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientResponseDTO.getDateOfBirth()));
        patient.setEmail(patientResponseDTO.getEmail());
        patient.setRegisteredDate(LocalDate.parse(patientResponseDTO.getRegisteredDate()));
        return patient;
    }


    
}
