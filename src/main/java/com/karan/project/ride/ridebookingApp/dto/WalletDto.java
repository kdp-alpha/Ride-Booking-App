package com.karan.project.ride.ridebookingApp.dto;

import com.karan.project.ride.ridebookingApp.entities.User;
import com.karan.project.ride.ridebookingApp.entities.WalletTransaction;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {

    private Long id;
    private User user;
    private Double balance;
    private List<WalletTransaction> transaction;
}
