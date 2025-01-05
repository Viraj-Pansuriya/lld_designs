package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String userId;
    private String vehicleRegistrationId;
    private String storeId;
    private LocalDateTime pickUpDateTime;
    private LocalDateTime dropOffDateTime;
}