package com.karan.project.ride.ridebookingApp.dto;


import com.karan.project.ride.ridebookingApp.entities.Rider;
import com.karan.project.ride.ridebookingApp.entities.enums.PaymentMethod;
import com.karan.project.ride.ridebookingApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {
    private Long id;
    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private LocalDateTime requestedTime;
    private RiderDto ride;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;


}
