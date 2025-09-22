package com.NextGenPay.service.customerMobileApp;
import com.NextGenPay.dto.request.CustomerLoginRequest;
import com.NextGenPay.dto.request.CustomerRegisterRequest;
import com.NextGenPay.dto.response.CustomerLoginResponse;
import com.NextGenPay.dto.response.CustomerRegisterResponse;



public interface CustomerAuthService {
    CustomerRegisterResponse registerCustomer(CustomerRegisterRequest RegisterRequest);
    CustomerLoginResponse loginCustomer(CustomerLoginRequest loginRequest);
}
