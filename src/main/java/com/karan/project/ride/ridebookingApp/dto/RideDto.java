package com.karan.project.ride.ridebookingApp.dto;

import com.karan.project.ride.ridebookingApp.entities.Driver;
import com.karan.project.ride.ridebookingApp.entities.enums.PaymentMethod;
import com.karan.project.ride.ridebookingApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
    @AllArgsConstructor
    @NoArgsConstructor
public class RideDto {

    private Long id;
    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDto ride;
    private DriverDto driver;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;

    private Double fare;
    private LocalDateTime startTime;
    private LocalDateTime endedAt;
}
