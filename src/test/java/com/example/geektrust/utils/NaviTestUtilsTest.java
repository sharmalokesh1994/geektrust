package com.example.geektrust.utils;

import com.example.geektrust.exceptions.NaviTestException;
import com.example.geektrust.model.LoanModel;
import com.example.geektrust.service.LoanQueryProcessorService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class NaviTestUtilsTest {

    @InjectMocks
    NaviTestUtils naviTestUtils;

    @Mock
    private LoanQueryProcessorService loanQueryProcessorService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validateQueryTest() {
        Mockito.when(loanQueryProcessorService.getLoanEntityByUserNameAndBankName(
                Mockito.anyString(),Mockito.anyString())).thenReturn(new LoanModel());
        naviTestUtils.validateQuery("bank","user");
        Mockito.verify(loanQueryProcessorService).getLoanEntityByUserNameAndBankName(Mockito.anyString(),Mockito.anyString());
    }

    @Test
    public void validateQueryExceptionTest() {
        Mockito.when(loanQueryProcessorService.getLoanEntityByUserNameAndBankName(
                Mockito.anyString(),Mockito.anyString())).thenReturn(null);

        Exception exception = null;
        try {
            naviTestUtils.validateQuery("bank","user");
        } catch (Exception ex) {
            exception = ex;
        }
        Assertions.assertNotNull(exception);
        Assertions.assertTrue(exception instanceof NaviTestException);

    }

}
