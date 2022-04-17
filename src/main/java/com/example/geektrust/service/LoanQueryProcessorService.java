package com.example.geektrust.service;

import com.example.geektrust.model.LoanModel;

public interface LoanQueryProcessorService {

    void processQuery(String query);

    LoanModel getLoanEntityByUserName(String userName);

    LoanModel getLoanEntityByUserNameAndBankName(String userName, String bankName);

}
