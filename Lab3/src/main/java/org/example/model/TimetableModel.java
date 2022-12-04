package org.example.model;


import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimetableModel {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime ldt;
}
