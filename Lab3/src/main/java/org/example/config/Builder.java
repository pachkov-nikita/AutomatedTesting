package org.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Doctor;
import org.example.entity.Patient;
import org.example.entity.TimeTable;
import org.example.repositrory.DoctorRepository;
import org.example.repositrory.PatientRepository;
import org.example.repositrory.TimetableRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class Builder  implements ApplicationListener<ApplicationReadyEvent> {

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    private final TimetableRepository timetableRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        doctorRepository.saveAll(buildDoctors());
        patientRepository.saveAll(buildPatients());
        timetableRepository.saveAll(buildTimetable());
    }

    private static List<Doctor> buildDoctors() {
        return List.of(
                Doctor.builder()
                        .firstName("Nikita")
                        .secondName("Pachkov")
                        .speciality("hiryrg")
                        .build(),
                Doctor.builder()
                        .firstName("Ivan")
                        .secondName("Ivanov")
                        .speciality("hiryrg")
                        .build(),
                Doctor.builder()
                        .firstName("Oleg")
                        .secondName("Olegov")
                        .speciality("lor")
                        .build()
        );
    }

    private static List<TimeTable> buildTimetable() {
        return List.of(
                TimeTable.builder()
                        .doctorId(1)
                        .ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 8, 0))
                        .build(),
                TimeTable.builder()
                        .doctorId(1)
                        .ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 9, 0))
                        .build(),
                TimeTable.builder()
                        .doctorId(1)
                        .ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 9, 30))
                        .vacant(false)
                        .patientId(1L)
                        .build(),
                TimeTable.builder()
                        .doctorId(2)
                        .ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 9, 15))
                        .vacant(false)
                        .patientId(2L)
                        .build(),
                TimeTable.builder()
                        .doctorId(2)
                        .ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 9, 45))
                        .vacant(false)
                        .patientId(4L)
                        .build(),
                TimeTable.builder()
                        .doctorId(2)
                        .ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 10, 0))
                        .build(),
                TimeTable.builder()
                        .doctorId(3)
                        .ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 9, 30))
                        .vacant(false)
                        .patientId(3L)
                        .build(),
                TimeTable.builder()
                        .doctorId(3)
                        .ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 10, 15))
                        .vacant(false)
                        .patientId(4L)
                        .build(),
                TimeTable.builder()
                        .doctorId(3)
                        .ldt(LocalDateTime.of(2022, Month.DECEMBER, 11, 11, 0))
                        .build()
        );
    }

    private List<Patient> buildPatients() {
        return List.of(
                Patient.builder()
                        .firstName("Tanya")
                        .secondName("One")
                        .build(),
                Patient.builder()
                        .firstName("Inna")
                        .secondName("Two")
                        .build(),
                Patient.builder()
                        .firstName("Katya")
                        .secondName("Three")
                        .build(),
                Patient.builder()
                        .firstName("Anya")
                        .secondName("Four")
                        .build(),
                Patient.builder()
                        .firstName("Gleb")
                        .secondName("Five")
                        .build()
        );
    }

}
