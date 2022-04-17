package com.example.geektrust.service;

import com.example.geektrust.model.PaymentModel;

import java.util.List;

public interface PaymentQueryProcessorService {

    void processQuery(String query);

    List<PaymentModel> getUserPaymentHistoryTillEmi(String userName, int emiNo);

}
