package com.karan.project.ride.ridebookingApp.strategies.impl;

import com.karan.project.ride.ridebookingApp.dto.RideRequestDto;
import com.karan.project.ride.ridebookingApp.entities.RideRequest;
import com.karan.project.ride.ridebookingApp.services.DistantService;
import com.karan.project.ride.ridebookingApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistantService distantService;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distant = distantService.calculateDistance(rideRequest.getDropOffLocation(),rideRequest.getPickupLocation());

        return distant*RIDE_FARE_MULTIPLIER;
    }
}
