package com.example.geektrust.service.impl;

import com.example.geektrust.exceptions.NaviTestException;
import com.example.geektrust.model.BalanceInputModel;
import com.example.geektrust.model.LoanModel;
import com.example.geektrust.model.PaymentModel;
import com.example.geektrust.service.BalanceQueryProcessorService;
import com.example.geektrust.service.LoanQueryProcessorService;
import com.example.geektrust.service.PaymentQueryProcessorService;
import com.example.geektrust.utils.NaviTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BalanceQueryProcessorServiceImpl implements BalanceQueryProcessorService {

    @Autowired
    private LoanQueryProcessorService loanQueryProcessorService;

    @Autowired
    private PaymentQueryProcessorService paymentQueryProcessorService;

    @Autowired
    private NaviTestUtils naviTestUtils;

    /**
     * process balance query.
     */
    @Override
    public String processQuery(String query) {


        BalanceInputModel balanceInputModel = null;
        try {
            String[] queryParams = query.split(" ");
            balanceInputModel = BalanceInputModel.builder().bankName(queryParams[1])
                    .userName(queryParams[2]).emiNo(Integer.parseInt(queryParams[3])).build();
        } catch (Exception ex) {
            // message can be more better here, it is just sample message
            throw new NaviTestException("query is not correct");
        }

        naviTestUtils.validateQuery(balanceInputModel.getBankName(),balanceInputModel.getUserName());

        LoanModel loanModel = loanQueryProcessorService.getLoanEntityByUserName(balanceInputModel.getUserName());

        List<PaymentModel> paymentModels = paymentQueryProcessorService.getUserPaymentHistoryTillEmi(
                balanceInputModel.getUserName(), balanceInputModel.getEmiNo());

        return buildBalanceQueryResponse(loanModel,paymentModels, balanceInputModel.getEmiNo());
    }

    private String buildBalanceQueryResponse(LoanModel loanModel, List<PaymentModel> paymentModels, int emiNo) {

        StringBuilder sb = new StringBuilder();
        sb.append(loanModel.getBankName() + " ");
        sb.append( loanModel.getUserName() +" " );

        int amountPaid = 0;
        for( PaymentModel paymentModel : paymentModels ) {
            amountPaid = amountPaid + paymentModel.getLumpSumAmount();
        }
        amountPaid = amountPaid + loanModel.getEmiAmount() * emiNo;

        sb.append(amountPaid + " ");
        int amountNeedToPay = loanModel.getTotalAmount() - amountPaid;
        int emiNeedToPay = (int) Math.ceil( (amountNeedToPay*1.0)/loanModel.getEmiAmount() );
        sb.append(emiNeedToPay+"");
        return sb.toString();
    }
}
