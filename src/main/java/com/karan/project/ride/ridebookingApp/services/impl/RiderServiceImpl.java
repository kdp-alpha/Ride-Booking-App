package com.karan.project.ride.ridebookingApp.services.impl;

import com.karan.project.ride.ridebookingApp.dto.RideDto;
import com.karan.project.ride.ridebookingApp.dto.RideRequestDto;
import com.karan.project.ride.ridebookingApp.dto.RiderDto;
import com.karan.project.ride.ridebookingApp.entities.*;
import com.karan.project.ride.ridebookingApp.entities.enums.RideRequestStatus;
import com.karan.project.ride.ridebookingApp.entities.enums.RideStatus;
import com.karan.project.ride.ridebookingApp.exceptions.ResourceNotFoundException;
import com.karan.project.ride.ridebookingApp.repositories.RideRequestRepo;
import com.karan.project.ride.ridebookingApp.repositories.RiderRepository;
import com.karan.project.ride.ridebookingApp.services.DriverService;
import com.karan.project.ride.ridebookingApp.services.RideRequestService;
import com.karan.project.ride.ridebookingApp.services.RideService;
import com.karan.project.ride.ridebookingApp.services.RiderService;
import com.karan.project.ride.ridebookingApp.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestService rideRequestService;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        log.info(rideRequest.toString());
        RideRequest savedRideRequest = rideRequestService.save(rideRequest);

        rideStrategyManager.driverMatchingStrategy(rider.getRatings()).findMatchingDrivers(rideRequest);

        return modelMapper.map(savedRideRequest,RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider does not own this ride: " + rideId);
        }


        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status: "+ ride.getRideStatus());
        }

        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver().getId(),true);

        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RiderDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider,RiderDto.class);
    }

    @Override
    public Page<RideDto> getMyRides(PageRequest pageRequest) {
        Rider currenRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currenRider,pageRequest).map(
                ride -> modelMapper.map(ride,RideDto.class)
        );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .ratings(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(() ->  new ResourceNotFoundException(
                "Rider Not Found With id:" + 1
        ));
    }
}
