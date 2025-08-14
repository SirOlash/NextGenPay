//package com.NextGenPay.service;
//import com.NextGenPay.data.repository.WalletRepository;
//import com.NextGenPay.dto.request.GenerateWalletRequest;
//import com.NextGenPay.dto.response.GenerateWalletResponse;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.math.BigDecimal;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class GenerateWalletServiceImplTest {
//
//    @Autowired
//    private WalletRepository  walletRepo;
//
//    @Autowired
//    private GenerateWalletServiceImpl service;
//
//    GenerateWalletResponse response;
//
//    @BeforeEach
//    void setUp() {
//        walletRepo.deleteAll();
//    }
//
//    @AfterEach
//    void tearDown() {
//        walletRepo.deleteAll();
//    }
//
//    @Test
//    void generateWallet_ShouldCreateWalletAndReturnExpectedResponse(){
//        String customerId = "12345678";
//        GenerateWalletRequest  request = new GenerateWalletRequest(customerId);
//        GenerateWalletResponse response = service.generateWallet(request);
//        assertEquals(1,walletRepo.count());
//        assertNotNull(response);
//        System.out.print(response.getAccountNumber());
//        assertEquals(10, response.getAccountNumber().length(), "Account number should be 10");
//        assertEquals(BigDecimal.ZERO, response.getBalance(), "Initial balance should be zero");
//    }
//}