package com.karan.project.ride.ridebookingApp.services;

import com.karan.project.ride.ridebookingApp.dto.DriverDto;
import com.karan.project.ride.ridebookingApp.dto.RideDto;
import com.karan.project.ride.ridebookingApp.dto.RideRequestDto;
import com.karan.project.ride.ridebookingApp.dto.RiderDto;
import com.karan.project.ride.ridebookingApp.entities.Rider;
import com.karan.project.ride.ridebookingApp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    RiderDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
