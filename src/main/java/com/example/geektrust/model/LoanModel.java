package com.example.geektrust.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanModel {

    private String userName;
    private String bankName;
    private int principalAmount;
    private int numberOfYears;
    private float interestRate;
    private int emiAmount;
    private int totalAmount;

}
