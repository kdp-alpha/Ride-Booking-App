package com.karan.project.ride.ridebookingApp.strategies;

import com.karan.project.ride.ridebookingApp.entities.enums.PaymentMethod;
import com.karan.project.ride.ridebookingApp.strategies.impl.CODPaymentStrategy;
import com.karan.project.ride.ridebookingApp.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CODPaymentStrategy codPaymentStrategy;



    public PaymentMethodStrategy paymentMethodStrategy(PaymentMethod paymentMethod){
        return switch(paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> codPaymentStrategy;
        };
    }
}
