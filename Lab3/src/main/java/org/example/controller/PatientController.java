package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Patient;
import org.example.entity.TimeTable;
import org.example.model.PatientModel;
import org.example.repositrory.PatientRepository;
import org.example.repositrory.TimetableRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {


    private final PatientRepository patientRepository;

    private final TimetableRepository timetableRepository;


    @GetMapping("/count")
    public List<Patient> getCountPatients() {
        return patientRepository.findAll();
    }

    @PostMapping("/secondname")
    public List<Patient> getDoctorBySecondName(@RequestBody PatientModel request) {
        return patientRepository.getPatientBySecondName(request.getSecondName());
    }

    @GetMapping("/{id}/timetable")
    public List<TimeTable> getTimetableByPatient(@PathVariable Long id) {
        return timetableRepository.findAllByPatientId(id);
    }

}
