package com.karan.project.ride.ridebookingApp.services;

import com.karan.project.ride.ridebookingApp.entities.Ride;
import com.karan.project.ride.ridebookingApp.entities.User;
import com.karan.project.ride.ridebookingApp.entities.Wallet;
import com.karan.project.ride.ridebookingApp.entities.enums.TransactionMethods;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethods transactionMethods);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride,TransactionMethods transactionMethods);

    void withdrawAllMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
