package com.NextGenPay.controller;
import com.NextGenPay.dto.request.CustomerLoginRequest;
import com.NextGenPay.dto.request.CustomerRegisterRequest;
import com.NextGenPay.dto.response.CustomerLoginResponse;
import com.NextGenPay.dto.response.CustomerRegisterResponse;
import com.NextGenPay.service.CustomerAuthServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerAuthController {

    private final CustomerAuthServiceImpl customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterResponse> registerCustomer(@RequestBody @Valid CustomerRegisterRequest request){
        return ResponseEntity.ok(customerService.registerCustomer(request));
    }
    @PostMapping("/login")
    public ResponseEntity<CustomerLoginResponse> loginCustomer(@RequestBody @Valid CustomerLoginRequest request){
        return ResponseEntity.ok(customerService.loginCustomer(request));
    }

}
