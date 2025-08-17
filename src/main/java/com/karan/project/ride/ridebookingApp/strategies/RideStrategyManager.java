package com.karan.project.ride.ridebookingApp.strategies;

import com.karan.project.ride.ridebookingApp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.karan.project.ride.ridebookingApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.karan.project.ride.ridebookingApp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.karan.project.ride.ridebookingApp.strategies.impl.RideFareSurgePricingCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareSurgePricingCalculationStrategy surgePricingCalculationStrategy;
    private final RideFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if(riderRating >= 4.8){
            return highestRatedDriverStrategy;
        }else{
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return surgePricingCalculationStrategy;
        }else{
            return defaultFareCalculationStrategy;
        }
    }
}
