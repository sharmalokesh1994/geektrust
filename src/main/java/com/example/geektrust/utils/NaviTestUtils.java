package com.example.geektrust.utils;

import com.example.geektrust.exceptions.NaviTestException;
import com.example.geektrust.service.LoanQueryProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NaviTestUtils {

    @Autowired
    private LoanQueryProcessorService loanQueryProcessorService;

    public void validateQuery(String bankName, String userName) {
        if( Objects.isNull(loanQueryProcessorService.getLoanEntityByUserNameAndBankName(userName,bankName)) ){
            throw new NaviTestException("query is not correct");
        }
    }

}
