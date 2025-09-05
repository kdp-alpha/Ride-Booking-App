package com.karan.project.ride.ridebookingApp.services;

import com.karan.project.ride.ridebookingApp.dto.DriverDto;
import com.karan.project.ride.ridebookingApp.dto.RideDto;
import com.karan.project.ride.ridebookingApp.dto.RiderDto;
import com.karan.project.ride.ridebookingApp.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DriverService {
    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId,String otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId,Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver,boolean availability);

    Driver createNewDriver(Driver driver);
}
