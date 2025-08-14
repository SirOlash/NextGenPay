//package com.NextGenPay.service;
//import com.NextGenPay.data.model.Wallet;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//class CustomerServiceImplTest {
//
//
//
//    @Autowired
//    CustomerServiceImpl customerService;
//
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    public void testCustomerCanDisplayWallet() {
//        Wallet foundWallet = customerService.displayWallet("68790c281f77993df088ecbd");
//        assertEquals(999000, foundWallet.getBalance());
//    }
//}