package com.example.geektrust.service.impl;

import com.example.geektrust.entity.PaymentEntity;
import com.example.geektrust.exceptions.NaviTestException;
import com.example.geektrust.model.PaymentModel;
import com.example.geektrust.repos.PaymentRepository;
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

public class PaymentQueryProcessorServiceImplTest {

    @InjectMocks
    PaymentQueryProcessorServiceImpl paymentQueryProcessorService;

    @Mock
    private PaymentRepository paymentRepository;

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
            paymentQueryProcessorService.processQuery("");
        } catch (Exception ex) {
            exception = ex;
        }

        Assertions.assertNotNull(exception);
        Assertions.assertTrue(exception instanceof NaviTestException);
    }

    @Test
    public void processQueryTest() {
        Mockito.doNothing().when(naviTestUtils).validateQuery(Mockito.anyString(),Mockito.anyString());
        Mockito.when(paymentRepository.save(Mockito.any())).thenReturn(null);
        paymentQueryProcessorService.processQuery("PAYMENT IDIDI Dale 1000 5");
        Mockito.verify(paymentRepository).save(Mockito.any());
    }

    @Test
    public void getUserPaymentHistoryTillEmiTest() {
        List<PaymentEntity> paymentEntities = new ArrayList<>();
        Mockito.when(paymentRepository.getPaymentByUserNameTillEmi(Mockito.any(),Mockito.anyInt()))
                .thenReturn(paymentEntities);
        List<PaymentModel> paymentModels = paymentQueryProcessorService.getUserPaymentHistoryTillEmi("t1",1);
        Assertions.assertEquals(0,paymentModels.size());

    }

}
