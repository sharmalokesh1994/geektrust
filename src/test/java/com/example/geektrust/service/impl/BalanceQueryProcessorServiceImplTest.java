package com.example.geektrust.service.impl;

import com.example.geektrust.exceptions.NaviTestException;
import com.example.geektrust.model.LoanModel;
import com.example.geektrust.model.PaymentModel;
import com.example.geektrust.service.LoanQueryProcessorService;
import com.example.geektrust.service.PaymentQueryProcessorService;
import com.example.geektrust.utils.NaviTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class BalanceQueryProcessorServiceImplTest {

    @InjectMocks
    BalanceQueryProcessorServiceImpl balanceQueryProcessorService;

    @Mock
    private LoanQueryProcessorService loanQueryProcessorService;

    @Mock
    private PaymentQueryProcessorService paymentQueryProcessorService;

    @Mock
    private NaviTestUtils naviTestUtils;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void processQueryValidationFailTest() {
        Exception exception = null;

        try {
            balanceQueryProcessorService.processQuery("");
        } catch (Exception ex) {
            exception = ex;
        }

        Assertions.assertNotNull(exception);
        Assertions.assertTrue(exception instanceof NaviTestException);
    }

    @Test
    public void processQueryTest() {
        Mockito.doNothing().when(naviTestUtils).validateQuery(Mockito.anyString(),Mockito.anyString());
        LoanModel loanModel = LoanModel.builder().bankName("IDIDI").userName("Dale")
                .emiAmount(500).totalAmount(10000).build();

        Mockito.when(loanQueryProcessorService.getLoanEntityByUserName(Mockito.anyString())).thenReturn(loanModel);
        List<PaymentModel> paymentModels = new ArrayList<>();
        paymentModels.add(PaymentModel.builder().lumpSumAmount(100).build());
        Mockito.when(paymentQueryProcessorService.getUserPaymentHistoryTillEmi(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(paymentModels);
        String response = balanceQueryProcessorService.processQuery("BALANCE IDIDI Dale 3");
        Assertions.assertTrue(response.length()>1);

    }



}
