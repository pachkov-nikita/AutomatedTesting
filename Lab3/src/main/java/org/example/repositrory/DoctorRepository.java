package org.example.repositrory;

import org.example.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> getDoctorByFirstName(String firstName);

    List<Doctor> getDoctorBySecondName(String secondName);

}