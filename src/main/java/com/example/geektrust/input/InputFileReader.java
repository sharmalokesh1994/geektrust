package com.example.geektrust.input;

import com.example.geektrust.exceptions.NaviTestException;
import com.example.geektrust.service.BalanceQueryProcessorService;
import com.example.geektrust.service.LoanQueryProcessorService;
import com.example.geektrust.service.PaymentQueryProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
@Slf4j
public class InputFileReader {

    @Autowired
    private LoanQueryProcessorService loanQueryProcessorService;

    @Autowired
    private PaymentQueryProcessorService paymentQueryProcessorService;

    @Autowired
    private BalanceQueryProcessorService balanceQueryProcessorService;

    public void readInput(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String query = reader.readLine();
            while (query != null) {
                processQuery(query);
                // read next query
                query = reader.readLine();
            }
        } catch (Exception e) {
            log.error("error : " + e.getMessage());
        }

    }

    private void processQuery(String query) {
        String queryParams[] = query.split(" ");
        // todo: use another StringUtils dependency and check

        if( StringUtils.isEmpty(queryParams[0])){
            throw new NaviTestException("query is not correct");
        } else if (queryParams[0].equalsIgnoreCase("BALANCE")) {
            System.out.println(balanceQueryProcessorService.processQuery(query));
        } else if ((queryParams[0].equalsIgnoreCase("LOAN"))) {
            loanQueryProcessorService.processQuery(query);
        } else if ( (queryParams[0].equalsIgnoreCase("PAYMENT"))) {
            paymentQueryProcessorService.processQuery(query);
        } else {
            throw new NaviTestException("query is not correct");
        }

    }

}
