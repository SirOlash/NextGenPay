package com.NextGenPay.data.repository;
import  com.NextGenPay.data.model.CustomerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerProfileRepo extends MongoRepository<CustomerProfile,String> {

//    boolean existsByUserName(String userName);

}
