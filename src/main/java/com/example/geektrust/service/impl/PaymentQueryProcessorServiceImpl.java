package com.example.geektrust.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.geektrust.entity.PaymentEntity;
import com.example.geektrust.exceptions.NaviTestException;
import com.example.geektrust.model.PaymentModel;
import com.example.geektrust.repos.PaymentRepository;
import com.example.geektrust.service.PaymentQueryProcessorService;
import com.example.geektrust.utils.NaviTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentQueryProcessorServiceImpl implements PaymentQueryProcessorService {

    @Autowired
    private PaymentRepository paymentRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private NaviTestUtils naviTestUtils;

    /**
     * process Payment query.
     */
    @Override
    public void processQuery(String query) {

        PaymentEntity paymentEntity = null;
        try {
            String[] queryParams = query.split(" ");

            paymentEntity = PaymentEntity.builder().bankName(queryParams[1]).userName(queryParams[2])
                    .lumpSumAmount(Integer.parseInt(queryParams[3])).emiNo(Integer.parseInt(queryParams[4])).build();
        } catch (Exception ex) {
            // message can be more better here, it is just sample message
            throw new NaviTestException("query is not correct");
        }
        naviTestUtils.validateQuery(paymentEntity.getBankName(),paymentEntity.getUserName());
        paymentRepository.save(paymentEntity);

    }

    @Override
    public List<PaymentModel> getUserPaymentHistoryTillEmi(String userName, int emiNo) {
        List<PaymentEntity> paymentEntities = paymentRepository.getPaymentByUserNameTillEmi(userName,emiNo);
        return mapper.convertValue(paymentEntities, new TypeReference<List<PaymentModel>>() {});
    }

}
