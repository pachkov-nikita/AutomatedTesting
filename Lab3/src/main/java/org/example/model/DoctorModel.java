package org.example.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorModel {
    private String firstName;
    private String secondName;
}