package com.example.geektrust.repos;

import com.example.geektrust.entity.PaymentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<PaymentEntity,Integer> {

    @Query(value= "select * from payment_entity where user_name=?1 and emi_no<=?2",nativeQuery = true)
    List<PaymentEntity> getPaymentByUserNameTillEmi( String userName,Integer emiNo );

}
