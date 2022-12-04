package org.example.repositrory;

import org.example.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Doctor, Long> {
}
