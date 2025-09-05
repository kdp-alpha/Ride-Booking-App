package com.karan.project.ride.ridebookingApp.services;

import com.karan.project.ride.ridebookingApp.dto.DriverDto;
import com.karan.project.ride.ridebookingApp.dto.SignupDto;
import com.karan.project.ride.ridebookingApp.dto.UserDto;


public interface AuthService {

    void login(String email,String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardingNewDriver(Long userId,String vehicleId);

}
