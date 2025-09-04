package com.karan.project.ride.ridebookingApp.strategies.impl;

import com.karan.project.ride.ridebookingApp.entities.Driver;
import com.karan.project.ride.ridebookingApp.entities.Payment;
import com.karan.project.ride.ridebookingApp.entities.Rider;
import com.karan.project.ride.ridebookingApp.entities.enums.PaymentStatus;
import com.karan.project.ride.ridebookingApp.entities.enums.TransactionMethods;
import com.karan.project.ride.ridebookingApp.repositories.PaymentRepository;
import com.karan.project.ride.ridebookingApp.services.PaymentService;
import com.karan.project.ride.ridebookingApp.services.WalletService;
import com.karan.project.ride.ridebookingApp.strategies.PaymentMethodStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Rider 232 rs in wallet Driver had 500
//Ride cost is 100, commission is 30
//Rider = 232 - 100 = 132
//Driver = 500 + (100 - commission) = 570

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentMethodStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        // get rider
        Rider rider = payment.getRide().getRider();

        // get driver
        Driver driver = payment.getRide().getDriver();

        walletService.deductMoneyFromWallet(rider.getUser(),
                payment.getAmount(),null,payment.getRide(), TransactionMethods.RIDE);

        double driverCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(),driverCut,null,payment.getRide(),TransactionMethods.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
