package com.karan.project.ride.ridebookingApp.strategies.impl;

import com.karan.project.ride.ridebookingApp.entities.Driver;
import com.karan.project.ride.ridebookingApp.entities.Payment;
import com.karan.project.ride.ridebookingApp.entities.Wallet;
import com.karan.project.ride.ridebookingApp.entities.enums.PaymentStatus;
import com.karan.project.ride.ridebookingApp.entities.enums.TransactionMethods;
import com.karan.project.ride.ridebookingApp.services.PaymentService;
import com.karan.project.ride.ridebookingApp.services.WalletService;
import com.karan.project.ride.ridebookingApp.strategies.PaymentMethodStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//Rider -> 100
//Driver -> 70 deduct 30rs from Driver's Wallet


@Service
@RequiredArgsConstructor
public class CODPaymentStrategy implements PaymentMethodStrategy {
    private final WalletService walletService;
    private final PaymentService paymentService;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platformCommission = payment.getAmount()*PLATFORM_COMMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(),platformCommission,null,payment.getRide(), TransactionMethods.RIDE);
        paymentService.updatePaymentStatus(payment, PaymentStatus.CONFIRMED);

    }
}
