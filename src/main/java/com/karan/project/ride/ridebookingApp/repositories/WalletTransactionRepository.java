package com.karan.project.ride.ridebookingApp.repositories;

import com.karan.project.ride.ridebookingApp.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {
}
