package com.karan.project.ride.ridebookingApp.strategies;

import com.karan.project.ride.ridebookingApp.dto.RideRequestDto;
import com.karan.project.ride.ridebookingApp.entities.RideRequest;

public interface RideFareCalculationStrategy {
    static final double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}
