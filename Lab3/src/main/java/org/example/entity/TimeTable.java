package org.example.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "timetable")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timetable_id")
    private long timetableId;

    @Column(name = "doctor_id")
    private long doctorId;

    @Column(name = "patient_id")
    private long patientId;

    @Column(name = "data")
    private LocalDateTime ldt;

    @Builder.Default
    @Column(name = "vacant")
    private boolean vacant = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) {
            Hibernate.getClass(this);
            Hibernate.getClass(o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}