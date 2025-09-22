package com.NextGenPay.controller;
import com.NextGenPay.dto.request.CreateProfileRequest;
import com.NextGenPay.service.customerMobileApp.CustomerProfileServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/customer-profile")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileServiceImpl customerProfileServiceImpl;


    @PostMapping("/create-Profile")
    public ResponseEntity<?> createProfile(@RequestBody @Valid CreateProfileRequest createProfileRequest){
        return  ResponseEntity.ok(customerProfileServiceImpl.createProfile(createProfileRequest));
    }

    @PatchMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid CreateProfileRequest createProfileRequest){
        return  ResponseEntity.ok(customerProfileServiceImpl.updateProfile(createProfileRequest));
    }

}
