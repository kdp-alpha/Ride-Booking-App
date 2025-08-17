package com.karan.project.ride.ridebookingApp.repositories;

import com.karan.project.ride.ridebookingApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long> {
}
