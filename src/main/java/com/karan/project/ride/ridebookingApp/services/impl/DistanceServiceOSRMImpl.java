package com.karan.project.ride.ridebookingApp.services.impl;

import com.karan.project.ride.ridebookingApp.services.DistantService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistantService {
    //call third party api for measuring distance
    private static final String OSRM_API = "http://router.project-osrm.org/route/v1/driving/";
    @Override
    public Double calculateDistance(Point src, Point dest) {
        try{

            String url = String.format("%s%f,%f;%f,%f",
                    OSRM_API,
                    src.getX(), src.getY(), // X = lon, Y = lat
                    dest.getX(), dest.getY()
            );
            OSRMResponseDto responseDto = RestClient.builder()
                    .baseUrl(OSRM_API)
                    .build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .body(OSRMResponseDto.class);

            return responseDto.getRoutes().get(0).getDistance() / 1000.0;
        }catch (Exception e){
            throw new RuntimeException("Error getting data from OSRM" + e.getMessage());
        }
    }
}

@Data
class OSRMResponseDto{
    private List<OSRMRoute> routes;
}

@Data
class OSRMRoute{
    private Double distance;
}