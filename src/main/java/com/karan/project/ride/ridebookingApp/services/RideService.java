package com.karan.project.ride.ridebookingApp.services;

import com.karan.project.ride.ridebookingApp.dto.RideRequestDto;
import com.karan.project.ride.ridebookingApp.entities.Driver;
import com.karan.project.ride.ridebookingApp.entities.Ride;
import com.karan.project.ride.ridebookingApp.entities.RideRequest;
import com.karan.project.ride.ridebookingApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}
