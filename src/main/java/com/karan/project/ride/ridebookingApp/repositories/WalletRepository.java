package com.karan.project.ride.ridebookingApp.repositories;

import com.karan.project.ride.ridebookingApp.entities.User;
import com.karan.project.ride.ridebookingApp.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUser(User user);
}
