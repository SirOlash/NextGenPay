package com.NextGenPay.controller;

import com.NextGenPay.dto.request.GenerateWalletRequest;
import com.NextGenPay.dto.response.GenerateWalletResponse;
import com.NextGenPay.service.GenerateWalletServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/createWallet")
@RequiredArgsConstructor
public class    GenerateWalletController {

    private final GenerateWalletServiceImpl service;
//todo 2 later
    @PostMapping
    public ResponseEntity<GenerateWalletResponse> createWallet(@RequestBody @Valid GenerateWalletRequest request){
        GenerateWalletResponse response = service.generateWallet(request);
        return ResponseEntity.ok(response);
    }
}
