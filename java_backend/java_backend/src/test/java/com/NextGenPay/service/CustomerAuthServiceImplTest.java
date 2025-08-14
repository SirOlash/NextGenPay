//package com.NextGenPay.service;
//import com.NextGenPay.data.repository.CustomerRepo;
//import com.NextGenPay.dto.request.CustomerLoginRequest;
//import com.NextGenPay.dto.request.CustomerRegisterRequest;
//import com.NextGenPay.dto.response.CustomerLoginResponse;
//import com.NextGenPay.exception.EmailAlreadyExistException;
//import com.NextGenPay.exception.InvalidPasswordException;
//import com.NextGenPay.exception.InvalidPhoneNumberException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//@SpringBootTest
//class CustomerAuthServiceImplTest {
//
//    @Autowired
//    private CustomerRepo customerRepo;
//
//    @Autowired
//    private CustomerAuthServiceImpl customerService;
//
//
//    @BeforeEach
//    void beforeEach(){
//        customerRepo.deleteAll();
//    }
//    @AfterEach
//    void afterEach(){
//        customerRepo.deleteAll();
//    }
//
//
//    @Test
//    public void testCustomerCanRegister() {
//        CustomerRegisterRequest request = new CustomerRegisterRequest();
//        request.setEmail("sammike@gmail.com");
//        request.setPhoneNumber("09096041561");
//        request.setPassword("0000");
//        customerService.registerCustomer(request);
//        assertEquals(1,customerRepo.count());
//   }
//
//   @Test
//   public void testCustomerCanLogin() {
//       CustomerRegisterRequest request = new CustomerRegisterRequest();
//       request.setEmail("Choko@gmail.com");
//       request.setPhoneNumber("09096041561");
//       request.setPassword("0000");
//       customerService.registerCustomer(request);
//       assertEquals(1,customerRepo.count());
//
//        CustomerLoginRequest request1 = new CustomerLoginRequest();
//        request1.setEmail("Choko@gmail.com");
//        request1.setPassword("0000");
//        CustomerLoginResponse response = customerService.loginCustomer(request1);
//        assertEquals("Success",response.getMessage());
//   }
//
//   @Test
//   public void testCustomerRegisterWithDuplicateEmail() {
//       CustomerRegisterRequest request = new CustomerRegisterRequest();
//       request.setEmail("sam@gmail.com");
//       request.setPhoneNumber("09098800188");
//       request.setPassword("0000");
//       customerService.registerCustomer(request);
//       assertEquals(1,customerRepo.count());
//
//       CustomerRegisterRequest request1 = new CustomerRegisterRequest();
//       request1.setEmail("sam@gmail.com");
//       request1.setPhoneNumber("09056424235");
//       request1.setPassword("0000");
//       assertThrows(EmailAlreadyExistException.class,()->customerService.registerCustomer(request1));
//    }
//    @Test
//    public void testCustomerRegisterWithInvalidPhoneNumber() {
//        CustomerRegisterRequest request = new CustomerRegisterRequest();
//        request.setEmail("sam@gmail.com");
//        request.setPhoneNumber(" ");
//        request.setPassword("0000");
//        assertThrows(InvalidPhoneNumberException.class,()-> customerService.registerCustomer(request));
//    }
//
//    @Test
//    public void testUserLoginWithInvalidEmail() {
//        CustomerRegisterRequest request = new CustomerRegisterRequest();
//        request.setEmail("Choko@gmail.com");
//        request.setPhoneNumber("09096041561");
//        request.setPassword("0000");
//        customerService.registerCustomer(request);
//        assertEquals(1,customerRepo.count());
//
//        CustomerLoginRequest request1 = new CustomerLoginRequest();
//        request1.setEmail("Choko@gmail.com");
//        request1.setPassword("1111");
//        assertThrows(InvalidPasswordException.class,()->customerService.loginCustomer(request1));
//    }
//
//}