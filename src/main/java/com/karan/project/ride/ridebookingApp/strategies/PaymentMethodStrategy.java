package com.karan.project.ride.ridebookingApp.strategies;

import com.karan.project.ride.ridebookingApp.entities.Payment;

public interface PaymentMethodStrategy {
    Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);
}
