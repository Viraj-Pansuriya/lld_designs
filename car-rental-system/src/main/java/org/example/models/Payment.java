package org.example.models;

import lombok.Data;

@Data
public class Payment {
    private Bill bill;
    private PaymentStatus paymentStatus;
}
