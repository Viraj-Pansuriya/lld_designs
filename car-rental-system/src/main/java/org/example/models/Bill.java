package org.example.models;

import lombok.Data;

@Data
public class Bill {
    private Registration registration;
    private double amount;
}
