package com.micro.patient_service.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.patient_service.Repository.PatientRepo;
import com.micro.patient_service.dtos.PatientRequestDTO;
import com.micro.patient_service.dtos.PatientResponseDTO;
import com.micro.patient_service.exception.EmailAlreadyExistException;
import com.micro.patient_service.exception.PatientNotFoundException;
import com.micro.patient_service.mapper.PatientMapper;
import com.micro.patient_service.model.Patient;



@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;

    public List<PatientResponseDTO> getPatient(){
        List<Patient> patients = patientRepo.findAll();
        List<PatientResponseDTO> patientResponseDTOs =patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
        return patientResponseDTOs;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepo.existsByEmailIgnoreCase(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("A patient with this email already exists"+ patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepo.save(PatientMapper.toPatient(patientRequestDTO));
        //email address must be unique
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id , PatientRequestDTO  patientRequestDTO ){
            Patient patient = patientRepo.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient Not found with ID: " + id.toString()));
            patient.setName(patientRequestDTO.getName());
            patient.setAddress(patientRequestDTO.getAddress());
            patient.setEmail(patientRequestDTO.getEmail());
            patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
            return PatientMapper.toDTO(patient);
    }
  
}


