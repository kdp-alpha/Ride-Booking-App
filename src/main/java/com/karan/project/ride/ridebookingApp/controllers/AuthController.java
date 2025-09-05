package com.karan.project.ride.ridebookingApp.controllers;

import com.karan.project.ride.ridebookingApp.dto.DriverDto;
import com.karan.project.ride.ridebookingApp.dto.OnboardNewDriver;
import com.karan.project.ride.ridebookingApp.dto.SignupDto;
import com.karan.project.ride.ridebookingApp.dto.UserDto;
import com.karan.project.ride.ridebookingApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto) {
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnboardNewDriver onboardNewDriver){
        return new ResponseEntity<>(authService.onboardingNewDriver(userId,
                onboardNewDriver.getVehicleId()), HttpStatus.CREATED);
    }

}
