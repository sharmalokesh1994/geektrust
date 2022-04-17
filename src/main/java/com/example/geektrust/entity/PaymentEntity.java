package com.example.geektrust.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String userName;

    @Column
    private String bankName;

    @Column
    private int lumpSumAmount;

    @Column
    private int emiNo;

}
