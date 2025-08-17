package com.karan.project.ride.ridebookingApp.repositories;

import com.karan.project.ride.ridebookingApp.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Query(value = """
    SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance
    FROM driver d
    WHERE d.available = true
      AND ST_DWithin(d.current_location, :pickupLocation, 1000)
    ORDER BY distance
    LIMIT 10
    """, nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickupLocation);

    @Query(value = """
     SELECT d.*
     FROM driver d
     WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000)
     ORDER BY ratings DESC
     LIMIT 10      
     """,nativeQuery = true)
    List<Driver> findTenNearByTopRatedDrivers(Point pickupLocation);
}
