package com.karan.project.ride.ridebookingApp.services.impl;

import com.karan.project.ride.ridebookingApp.dto.DriverDto;
import com.karan.project.ride.ridebookingApp.dto.RideDto;
import com.karan.project.ride.ridebookingApp.dto.RiderDto;
import com.karan.project.ride.ridebookingApp.entities.Driver;
import com.karan.project.ride.ridebookingApp.entities.Ride;
import com.karan.project.ride.ridebookingApp.entities.RideRequest;
import com.karan.project.ride.ridebookingApp.entities.enums.RideRequestStatus;
import com.karan.project.ride.ridebookingApp.entities.enums.RideStatus;
import com.karan.project.ride.ridebookingApp.exceptions.ResourceNotFoundException;
import com.karan.project.ride.ridebookingApp.repositories.DriverRepository;
import com.karan.project.ride.ridebookingApp.services.DriverService;
import com.karan.project.ride.ridebookingApp.services.RideRequestService;
import com.karan.project.ride.ridebookingApp.services.RideService;
import com.karan.project.ride.ridebookingApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("RideRequest cannot be accepted, status is "+ rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();

        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability: "+ currentDriver.getId());
        }

        currentDriver.setAvailable(false);
       Driver savedDriver = driverRepository.save(currentDriver);
      Ride ride = rideService.createNewRide(rideRequest,savedDriver);

        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot cancel a ride as he has not accepted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status: "+ ride.getRideStatus());
        }

        rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        driver.setAvailable(true);
        driverRepository.save(driver);

        return modelMapper.map(ride,RideDto.class);

    }

    @Override
    public RideDto startRide(Long rideId,String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride Status is not confirmed "+ ride.getRideStatus());
        }

        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("Otp is not valid "+ otp);

        }
        ride.setStartedAt(LocalDateTime.now());

        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.ONGOING);

        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        Driver driver = getCurrentDriver();
        return modelMapper.map(driver,DriverDto.class);
    }

    @Override
    public Page<RideDto> getMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver.getId(),pageRequest).map(
                ride -> modelMapper.map(ride,RideDto.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException(
                "Driver not found with id " +  2
        ));
    }

    @Override
    public void updateDriverAvailability(Long driverId, boolean availability) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() ->
         new ResourceNotFoundException("Driver not found with id: "+ driverId)
        );

        driver.setAvailable(availability);
        driverRepository.save(driver);

    }
}
