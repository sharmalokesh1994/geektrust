package com.example.geektrust.repos;

import com.example.geektrust.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<LoanEntity,Integer> {

    LoanEntity findByUserName(String userName);

    LoanEntity findByUserNameAndBankName(String userName, String bankName);

}
