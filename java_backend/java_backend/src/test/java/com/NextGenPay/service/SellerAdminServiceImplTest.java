//package com.NextGenPay.service;
//import com.NextGenPay.data.model.BusinessType;
//import com.NextGenPay.data.repository.SellerAdminRepository;
//import com.NextGenPay.dto.request.CreateCashierRequest;
//import com.NextGenPay.dto.request.SellerAdminLoginRequest;
//import com.NextGenPay.dto.request.SellerAdminRegisterRequest;
//import com.NextGenPay.dto.response.CreateCashierResponse;
//import com.NextGenPay.dto.response.SellerAdminLoginResponse;
//import com.NextGenPay.dto.response.SellerAdminRegisterResponse;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//class SellerAdminServiceImplTest {
//
//    @Autowired
//    private SellerAdminServiceImpl sellerAdminService;
//
//    @Autowired
//    private SellerAdminRepository sellerRepo;
//
//    @BeforeEach
//    void setUp() {
//        sellerRepo.deleteAll();
//    }
//
//    @AfterEach
//    void tearDown() {
//        sellerRepo.deleteAll();
//    }
//
//    @Test
//    public void testSellerAdminCanRegister() {
//
//        SellerAdminRegisterRequest request = new SellerAdminRegisterRequest();
//        request.setBusinessName("main steward");
//        request.setBusinessType(BusinessType.REGISTERED);
//        request.setFirstName("Aboje");
//        request.setLastName("Edwin");
//        request.setPassword("password");
//        request.setEmail("abojeedwin@gmail.com");
//        SellerAdminRegisterResponse response = sellerAdminService.registerSellerAdmin(request);
//        assertEquals(1,sellerRepo.count());
//        assertEquals(response.getMessage(),"Admin registered successfully");
//    }
//
//    @Test
//    public void testSellerAdminCanLogin() {
//
//        SellerAdminRegisterRequest request = new SellerAdminRegisterRequest();
//        request.setBusinessName("main steward");
//        request.setBusinessType(BusinessType.REGISTERED);
//        request.setFirstName("Aboje");
//        request.setLastName("Edwin");
//        request.setPassword("password");
//        request.setEmail("abojeedwin@gmail.com");
//        SellerAdminRegisterResponse response = sellerAdminService.registerSellerAdmin(request);
//        assertEquals(1,sellerRepo.count());
//        assertEquals(response.getMessage(),"Admin registered successfully");
//
//        SellerAdminLoginRequest request1 = new SellerAdminLoginRequest();
//        request1.setEmail("abojeedwin@gmail.com");
//        request1.setPassword("password");
//        SellerAdminLoginResponse response1 = sellerAdminService.loginSellerAdmin(request1);
//        assert(response1.getMessage().equals("Success"));
//    }
//
//    @Test
//    public void testSellerAdminCreateCashier(){
//
//        SellerAdminRegisterRequest request = new SellerAdminRegisterRequest();
//        request.setBusinessName("main steward");
//        request.setBusinessType(BusinessType.REGISTERED);
//        request.setFirstName("Aboje");
//        request.setLastName("Edwin");
//        request.setPassword("password");
//        request.setEmail("abojeedwin@gmail.com");
//        SellerAdminRegisterResponse response = sellerAdminService.registerSellerAdmin(request);
//
//
////        CreateCashierRequest request1 = new CreateCashierRequest();
////        request1.setDateRegistered(LocalDate.now());
////        request1.setPhoneNumber("112223333");
////        request1.setUserName("cashier wey sure");
////        request1.setSellerAdminId(response.getSellerAdminId());
////        CreateCashierResponse response1 = sellerAdminService.createCashier(request1);
////        assert(response1.getMessage().equals("Cashier created successfully"));
//    }
//}