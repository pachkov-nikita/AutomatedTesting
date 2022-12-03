package org.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Doctor;
import org.example.repositrory.DoctorRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class Builder  implements ApplicationListener<ApplicationReadyEvent> {

    private final DoctorRepository doctorRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        doctorRepository.saveAll(buildDoctors());
    }

    private static List<Doctor> buildDoctors() {
        return List.of(
                Doctor.builder()
                        .firstName("Nikita")
                        .secondName("Pachkov")
                        .build(),
                Doctor.builder()
                        .firstName("Ivan")
                        .secondName("Ivanov")
                        .build(),
                Doctor.builder()
                        .firstName("Oleg")
                        .secondName("Olegov")
                        .build()
        );
    }

}
