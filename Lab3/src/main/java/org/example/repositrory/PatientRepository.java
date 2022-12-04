package org.example.repositrory;

import org.example.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> getPatientBySecondName(String secondName);
}
