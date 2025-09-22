package com.NextGenPay.controller;
import com.NextGenPay.dto.request.AddFundsRequest;
import com.NextGenPay.dto.response.AddFundsResponse;
import com.NextGenPay.service.customerMobileApp.AddFundsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/addFunds")
@RequiredArgsConstructor
public class AddFundsController {
    private final AddFundsServiceImpl service;

    @PostMapping
    public ResponseEntity<AddFundsResponse> addFunds(@RequestBody @Valid AddFundsRequest request){
        AddFundsResponse response = service.addFunds(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
