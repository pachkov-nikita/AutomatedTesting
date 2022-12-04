package org.example.repositrory;

import org.example.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TimetableRepository extends JpaRepository<TimeTable, Long> {
    List<TimeTable> findAllByDoctorId(Long doctorId);
    List<TimeTable> findAllByPatientId(Long patientId);
    TimeTable findFirstByDoctorIdAndLdt(Long doctorId, LocalDateTime time);
}
