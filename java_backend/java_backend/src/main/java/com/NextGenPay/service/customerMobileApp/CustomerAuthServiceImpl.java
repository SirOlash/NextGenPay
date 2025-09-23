package com.NextGenPay.service.customerMobileApp;
import com.NextGenPay.data.model.Customer;
import com.NextGenPay.data.model.CustomerStatus;
import com.NextGenPay.data.repository.CustomerProfileRepo;
import com.NextGenPay.data.repository.CustomerRepo;
import com.NextGenPay.dto.request.CustomerLoginRequest;
import com.NextGenPay.dto.request.CustomerRegisterRequest;
import com.NextGenPay.dto.response.CustomerLoginResponse;
import com.NextGenPay.dto.response.CustomerRegisterResponse;
import com.NextGenPay.exception.*;
import com.NextGenPay.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CustomerAuthServiceImpl implements CustomerAuthService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private VerifyPhone verifyPhone;
    @Autowired
    private HashPassword hashPassword;

    @Autowired
    private JwtAuth jwtAuth;

    @Autowired
    private CustomerProfileRepo customerProfileRepo;




    @Override
    public CustomerRegisterResponse registerCustomer(CustomerRegisterRequest registerRequest) {
        if(customerRepo.existsByEmail(registerRequest.getEmail())){
           throw new EmailAlreadyExistException("Email already exists");}
        if(!verifyPhone.isVerifiedPhoneNumber(registerRequest.getPhoneNumber())){throw new InvalidPhoneNumberException("Invalid phone number");}
        String hashedPassword = HashPassword.hashPassword(registerRequest.getPassword());

       Customer customer = new Customer();
       customer.setEmail(registerRequest.getEmail());
       customer.setPhoneNumber(registerRequest.getPhoneNumber());
       customer.setFirstName(registerRequest.getFirstName());
       customer.setLastName(registerRequest.getLastName());
       customer.setCustomerStatus(CustomerStatus.ACTIVE);
       customer.setPassword(hashedPassword);
       Customer savedCustomer = customerRepo.save(customer);
       String message = "Customer registered successfully";

        return new CustomerRegisterResponse(message,savedCustomer.getCustomerId());
    }

    @Override
    public CustomerLoginResponse loginCustomer(CustomerLoginRequest loginRequest) {
        Customer foundCustomer = customerRepo.findByEmail(loginRequest.getEmail());
        if(foundCustomer == null || !HashPassword.verifyPassword(foundCustomer.getPassword(),loginRequest.getPassword()) ){
            throw new InvalidPasswordException("Invalid Credentials");}
//        if(foundCustomer == null){
//            throw new CustomerNotFoundException("No Customer Found");
//        }
        String token = jwtAuth.generateToken(loginRequest.getEmail());
        CustomerLoginResponse loginResponse = new CustomerLoginResponse();
        loginResponse.setToken(token);
        loginResponse.setMessage("Success");
        return new CustomerLoginResponse(loginResponse.getMessage(),loginResponse.getToken());

    }
}
