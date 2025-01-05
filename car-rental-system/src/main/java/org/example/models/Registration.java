package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registration {
    private String registrationId;
    private User user;
    private Vehicle vehicle;
    private LocalDateTime registrationDate;
    private LocalDateTime pickUpDateTime;
    private LocalDateTime dropOffDateTime;
    private RegistrationStatus registrationStatus;
}
