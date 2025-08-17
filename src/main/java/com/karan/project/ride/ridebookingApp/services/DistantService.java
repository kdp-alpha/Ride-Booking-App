package com.karan.project.ride.ridebookingApp.services;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;


public interface DistantService {

    Double calculateDistance(Point src,Point dest);
}
