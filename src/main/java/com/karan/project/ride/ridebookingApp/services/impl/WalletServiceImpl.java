package com.karan.project.ride.ridebookingApp.services.impl;

import com.karan.project.ride.ridebookingApp.dto.RideDto;
import com.karan.project.ride.ridebookingApp.dto.WalletTransactionDto;
import com.karan.project.ride.ridebookingApp.entities.Ride;
import com.karan.project.ride.ridebookingApp.entities.User;
import com.karan.project.ride.ridebookingApp.entities.Wallet;
import com.karan.project.ride.ridebookingApp.entities.WalletTransaction;
import com.karan.project.ride.ridebookingApp.entities.enums.TransactionMethods;
import com.karan.project.ride.ridebookingApp.entities.enums.TransactionType;
import com.karan.project.ride.ridebookingApp.exceptions.ResourceNotFoundException;
import com.karan.project.ride.ridebookingApp.repositories.WalletRepository;
import com.karan.project.ride.ridebookingApp.services.WalletService;
import com.karan.project.ride.ridebookingApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride,TransactionMethods transactionMethods
    ) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethods(transactionMethods)
                .amount(amount)
                .build();

    walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);

    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride,TransactionMethods transactionMethods) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethods(transactionMethods)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() ->
            new ResourceNotFoundException("Wallet Not found exception with wallet id: " + walletId)
        );
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(() ->
                new ResourceNotFoundException("Wallet not found with this particular user: "+ user.getId())
        );
    }
}
