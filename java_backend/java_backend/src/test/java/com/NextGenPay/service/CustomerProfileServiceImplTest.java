//package com.NextGenPay.service;
//import com.NextGenPay.data.repository.CustomerProfileRepo;
//import com.NextGenPay.dto.request.CreateProfileRequest;
//import com.NextGenPay.dto.response.CreateProfileResponse;
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
//@SpringBootTest
//class CustomerProfileServiceImplTest {
//
//    @Autowired
//    private CustomerProfileServiceImpl customerProfileServiceImpl;
//
//    @Autowired
//    private CustomerProfileRepo customerProfileRepo;
//
//    @BeforeEach
//    void BeforeEach() {
//        customerProfileRepo.deleteAll();
//    }
//
//    @AfterEach
//    void tearDown() {
//        customerProfileRepo.deleteAll();
//    }
//
//    @Test
//    public void testCustomerCreateProfile(){
//        CreateProfileRequest request = new CreateProfileRequest();
//        request.setAddress("17 olatunji bariga");
//        request.setFirstName("Edwin");
//        request.setLastName("Aboje");
//        request.setUserName("Dark12");
//        request.setCustomerId("68740d3cea504106ffbff56e");
//        request.setDateOfBirth(LocalDate.of(1990,03,22));
//        customerProfileServiceImpl.createProfile(request);
//        assertEquals(1,customerProfileRepo.count());
//
//    }
//    @Test
//    public void testCustomerUpdateProfile(){
//        assertEquals(0,customerProfileRepo.count());
//        CreateProfileRequest request = new CreateProfileRequest();
//        request.setAddress("17 olatunji bariga");
//        request.setFirstName("Edwin");
//        request.setLastName("Aboje");
//        request.setUserName("NewDark12");
//        request.setDateOfBirth(LocalDate.of(1990,03,22));
//        customerProfileServiceImpl.updateProfile(request);
//        assertEquals(1,customerProfileRepo.count());
//    }
//
//    @Test
//    public void testCustomerFindProfileById(){
//        assertEquals(0,customerProfileRepo.count());
//        CreateProfileRequest request = new CreateProfileRequest();
//        request.setAddress("17 olatunji bariga");
//        request.setFirstName("Edwin");
//        request.setLastName("Aboje");
//        request.setUserName("NewDark12");
//        request.setDateOfBirth(LocalDate.of(1990,03,22));
//        CreateProfileResponse response = customerProfileServiceImpl.createProfile(request);
//        assertEquals(1,customerProfileRepo.count());
//
//    }
//}