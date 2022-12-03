package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Doctor;
import org.example.model.DoctorModel;
import org.example.repositrory.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorRepository doctorRepository;

    @PostMapping("/firstname")
    public List<Doctor> getDoctorByName(@RequestBody DoctorModel request) {
        return doctorRepository.getDoctorByFirstName(request.getFirstName());
    }

    @PostMapping("/secondname")
    public List<Doctor> getDoctorBySecondName(@RequestBody DoctorModel request) {
        return doctorRepository.getDoctorBySecondName(request.getSecondName());
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @GetMapping("/count")
    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

}
