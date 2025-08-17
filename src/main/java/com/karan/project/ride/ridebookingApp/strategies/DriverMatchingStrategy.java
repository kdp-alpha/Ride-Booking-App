package com.karan.project.ride.ridebookingApp.strategies;

import com.karan.project.ride.ridebookingApp.dto.RideRequestDto;
import com.karan.project.ride.ridebookingApp.entities.Driver;
import com.karan.project.ride.ridebookingApp.entities.RideRequest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverMatchingStrategy {
     List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
