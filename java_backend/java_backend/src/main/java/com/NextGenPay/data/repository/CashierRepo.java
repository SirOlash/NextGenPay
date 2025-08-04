package com.NextGenPay.data.repository;
import com.NextGenPay.data.model.Cashier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashierRepo extends MongoRepository<Cashier, String> {

    Cashier findByCashierId(String cashierId);
    List<Cashier> findBySellerAdminId(String sellerAdminId);
}
