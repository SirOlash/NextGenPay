package com.NextGenPay.data.repository;
import com.NextGenPay.data.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends MongoRepository<Wallet,String> {
    Optional<Wallet> findByAccountNumber(String accountNumber);
    Wallet findByCustomerId(String customerId);
}
