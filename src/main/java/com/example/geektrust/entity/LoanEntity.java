package com.example.geektrust.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanEntity {

    @Id
    @GeneratedValue
    private int id;

    // considering that user is allowed to take only one loan
    @Column(unique = true)
    private String userName;

    @Column
    private String bankName;

    @Column
    private int principalAmount;

    @Column
    private int numberOfYears;

    @Column
    private float interestRate;

    @Column
    private int emiAmount;

    @Column
    private int totalAmount;

}
