package com.karan.project.ride.ridebookingApp.strategies.impl;

import com.karan.project.ride.ridebookingApp.entities.RideRequest;
import com.karan.project.ride.ridebookingApp.services.DistantService;
import com.karan.project.ride.ridebookingApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingCalculationStrategy implements RideFareCalculationStrategy {
    private final DistantService distantService;
    private static final double SURGE_FACTOR = 2;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distant = distantService.calculateDistance(rideRequest.getDropOffLocation(),rideRequest.getPickupLocation());

        return distant*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
