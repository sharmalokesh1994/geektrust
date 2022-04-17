package com.example.geektrust.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.geektrust.constants.Constant;
import com.example.geektrust.entity.LoanEntity;
import com.example.geektrust.exceptions.NaviTestException;
import com.example.geektrust.model.LoanModel;
import com.example.geektrust.repos.LoanRepository;
import com.example.geektrust.service.LoanQueryProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class LoanQueryProcessorServiceImpl implements LoanQueryProcessorService {

    @Autowired
    private LoanRepository loanRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * process Loan Query.
     */
    @Override
    public void processQuery(String query) {

        LoanEntity loanEntity = null;

        try {
            String[] queryParams = query.split(" ");

            loanEntity = LoanEntity.builder().bankName(queryParams[1])
                    .userName(queryParams[2]).principalAmount(Integer.parseInt(queryParams[3]))
                    .numberOfYears(Integer.parseInt(queryParams[4])).interestRate(Float.parseFloat(queryParams[5])).build();
        } catch (Exception ex) {
            // message can be more better here, it is just sample message
            throw new NaviTestException("query is not correct");
        }

        loanEntity.setTotalAmount(calculateTotalAmount(loanEntity.getPrincipalAmount(),
                loanEntity.getInterestRate(),loanEntity.getNumberOfYears()));

        loanEntity.setEmiAmount(calculateEmiAmount(loanEntity.getTotalAmount(),loanEntity.getNumberOfYears()));

        loanRepository.save(loanEntity);
        log.info("loan has granted");

    }

    public LoanModel getLoanEntityByUserName(String userName) {
        LoanEntity loanEntity = loanRepository.findByUserName(userName);
        if(Objects.isNull(loanEntity)) {
            return null;
        }
        return objectMapper.convertValue(loanEntity,LoanModel.class);
    }

    @Override
    public LoanModel getLoanEntityByUserNameAndBankName(String userName, String bankName) {
        LoanEntity loanEntity = loanRepository.findByUserNameAndBankName(userName,bankName);
        if(Objects.isNull(loanEntity)) {
            return null;
        }
        return objectMapper.convertValue(loanEntity,LoanModel.class);
    }

    private int calculateTotalAmount( int principalAmount, float interestRate, int years ) {

        return (int) Math.ceil(principalAmount+(principalAmount*interestRate*years)/100);
    }

    private int calculateEmiAmount( int totalAmount, int years ) {

        return (int) Math.ceil((totalAmount*1.0)/(years* Constant.NO_OF_MONTHS_IN_YEAR));
    }
}
