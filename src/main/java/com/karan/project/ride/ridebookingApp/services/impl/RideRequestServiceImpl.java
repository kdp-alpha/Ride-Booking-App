package com.karan.project.ride.ridebookingApp.services.impl;

import com.karan.project.ride.ridebookingApp.entities.RideRequest;
import com.karan.project.ride.ridebookingApp.exceptions.ResourceNotFoundException;
import com.karan.project.ride.ridebookingApp.repositories.RideRequestRepo;
import com.karan.project.ride.ridebookingApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
    private final RideRequestRepo rideRequestRepo;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepo.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id:"+ rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepo.findById(rideRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Riderequest not found " + rideRequest.getId()));


        rideRequestRepo.save(rideRequest);
    }
}
