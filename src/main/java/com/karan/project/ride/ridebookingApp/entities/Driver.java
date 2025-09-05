package com.karan.project.ride.ridebookingApp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double ratings;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private Boolean available;

    @Column(columnDefinition = "Geometry(Point,4326)")
    Point currentLocation;

    private String vehicleId;

}
