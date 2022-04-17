package com.example.geektrust.service.impl;

import com.example.geektrust.entity.LoanEntity;
import com.example.geektrust.exceptions.NaviTestException;
import com.example.geektrust.model.LoanModel;
import com.example.geektrust.repos.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoanQueryProcessorServiceImplTest {

    @InjectMocks
    LoanQueryProcessorServiceImpl loanQueryProcessorService;

    @Mock
    private LoanRepository loanRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void processQueryValidationFailTest() {
        Exception exception = null;

        try {
            loanQueryProcessorService.processQuery("");
        } catch (Exception ex) {
            exception = ex;
        }

        Assertions.assertNotNull(exception);
        Assertions.assertTrue(exception instanceof NaviTestException);
    }

    @Test
    public void processQueryTest() {
        Mockito.when(loanRepository.save(Mockito.any())).thenReturn(null);
        loanQueryProcessorService.processQuery("LOAN IDIDI Dale 5000 1 6");
        Mockito.verify(loanRepository).save(Mockito.any());
    }

    @Test
    public void getLoanEntityByUserNameReturnNullTest() {
        Mockito.when(loanRepository.findByUserName(Mockito.anyString())).thenReturn(null);
        loanQueryProcessorService.getLoanEntityByUserName("a");
        Mockito.verify(loanRepository).findByUserName(Mockito.anyString());
    }

    @Test
    public void getLoanEntityByUserNamelTest() {
        LoanEntity loanEntity = LoanEntity.builder().bankName("IDIDI").userName("Dale")
                .emiAmount(500).totalAmount(10000).build();
        Mockito.when(loanRepository.findByUserName(Mockito.anyString())).thenReturn(loanEntity);
        LoanModel loanModel = loanQueryProcessorService.getLoanEntityByUserName("a");
        Assertions.assertEquals(10000,loanModel.getTotalAmount());
    }

    @Test
    public void getLoanEntityByUserNameAndBankNameReturnNullTest() {
        Mockito.when(loanRepository.findByUserNameAndBankName(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(null);
        loanQueryProcessorService.getLoanEntityByUserNameAndBankName("a","a");
        Mockito.verify(loanRepository).findByUserNameAndBankName(Mockito.anyString(),Mockito.anyString());
    }

    @Test
    public void getLoanEntityByUserNameAndBankNameTest() {
        LoanEntity loanEntity = LoanEntity.builder().bankName("IDIDI").userName("Dale")
                .emiAmount(500).totalAmount(10000).build();
        Mockito.when(loanRepository.findByUserNameAndBankName(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(loanEntity);
        LoanModel loanModel = loanQueryProcessorService.getLoanEntityByUserNameAndBankName("a","a");
        Assertions.assertEquals(10000,loanModel.getTotalAmount());
    }

}
