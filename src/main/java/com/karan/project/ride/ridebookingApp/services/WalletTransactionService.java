package com.karan.project.ride.ridebookingApp.services;

import com.karan.project.ride.ridebookingApp.dto.WalletTransactionDto;
import com.karan.project.ride.ridebookingApp.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
