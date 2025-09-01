package com.karan.project.ride.ridebookingApp.repositories;

import com.karan.project.ride.ridebookingApp.entities.Payment;
import com.karan.project.ride.ridebookingApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByRide(Ride ride);
}
