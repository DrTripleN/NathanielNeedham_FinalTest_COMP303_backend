package com.example.comp303testfinal_needham.Repository;

import com.example.comp303testfinal_needham.Entity.Banks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BankRepository extends MongoRepository<Banks, String> {
    List<Banks> findByBankName(String bankName);
}
