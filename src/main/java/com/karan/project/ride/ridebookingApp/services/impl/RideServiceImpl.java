package com.karan.project.ride.ridebookingApp.services.impl;

import com.karan.project.ride.ridebookingApp.dto.RideRequestDto;
import com.karan.project.ride.ridebookingApp.entities.*;
import com.karan.project.ride.ridebookingApp.entities.enums.RideRequestStatus;
import com.karan.project.ride.ridebookingApp.entities.enums.RideStatus;
import com.karan.project.ride.ridebookingApp.exceptions.ResourceNotFoundException;
import com.karan.project.ride.ridebookingApp.repositories.RideRepository;
import com.karan.project.ride.ridebookingApp.services.RideRequestService;
import com.karan.project.ride.ridebookingApp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {

        return rideRepository.findById(rideId).orElseThrow(() ->
              new ResourceNotFoundException("Ride not found with id: " + rideId)
        );
    }


    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest,Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOtp());
        ride.setId(null);

        rideRequestService.update(rideRequest);

        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }

    private String generateRandomOtp(){
        Random random = new Random();
        int otp = random.nextInt(1000);
        return String.format("%04d",otp);
    }
}
