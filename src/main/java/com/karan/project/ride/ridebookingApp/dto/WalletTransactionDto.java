package com.karan.project.ride.ridebookingApp.dto;

import com.karan.project.ride.ridebookingApp.entities.Ride;
import com.karan.project.ride.ridebookingApp.entities.Wallet;
import com.karan.project.ride.ridebookingApp.entities.enums.TransactionMethods;
import com.karan.project.ride.ridebookingApp.entities.enums.TransactionType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransactionDto {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethods transactionMethods;
    private Ride ride;
    private String transactionId;
    private WalletDto wallet;
    private LocalDateTime timestamp;
}
