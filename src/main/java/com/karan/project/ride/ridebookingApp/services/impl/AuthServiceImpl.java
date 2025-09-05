package com.karan.project.ride.ridebookingApp.services.impl;

import com.karan.project.ride.ridebookingApp.dto.DriverDto;
import com.karan.project.ride.ridebookingApp.dto.SignupDto;
import com.karan.project.ride.ridebookingApp.dto.UserDto;
import com.karan.project.ride.ridebookingApp.entities.Driver;
import com.karan.project.ride.ridebookingApp.entities.Rider;
import com.karan.project.ride.ridebookingApp.entities.User;
import com.karan.project.ride.ridebookingApp.entities.enums.Role;
import com.karan.project.ride.ridebookingApp.exceptions.ResourceNotFoundException;
import com.karan.project.ride.ridebookingApp.exceptions.RuntimeConflictException;
import com.karan.project.ride.ridebookingApp.repositories.UserRepository;
import com.karan.project.ride.ridebookingApp.services.AuthService;
import com.karan.project.ride.ridebookingApp.services.DriverService;
import com.karan.project.ride.ridebookingApp.services.RiderService;
import com.karan.project.ride.ridebookingApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private  final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    @Override
    public void login(String email, String password) {

    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElseThrow(()->
                new RuntimeConflictException("Cannot signup, User already exist wit email" + signupDto.getEmail())
        );

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        //while signup every user will be rider so we have to create a rider
         riderService.createNewRider(savedUser);
         walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardingNewDriver(Long userId,String vehicleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id" + userId));
        if(user.getRoles().contains(Role.DRIVER)){
            throw new RuntimeConflictException("User with id" + userId + "is already a driver");
        }

        Driver driver = Driver.builder()
                .user(user)
                .ratings(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();

        user.getRoles().add(Role.DRIVER);

        Driver savedDriver = driverService.createNewDriver(driver);

        return modelMapper.map(savedDriver,DriverDto.class);

    }
}
