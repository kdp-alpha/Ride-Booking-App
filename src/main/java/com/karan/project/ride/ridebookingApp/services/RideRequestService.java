package com.karan.project.ride.ridebookingApp.services;

import com.karan.project.ride.ridebookingApp.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
