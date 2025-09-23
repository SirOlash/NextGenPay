package com.NextGenPay.controller;

import com.NextGenPay.dto.response.GenerateApiKeyResponse;
import com.NextGenPay.service.sellerWebApp.GenerateApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class GenerateApiKeyController {

    private final GenerateApiKeyService service;

    @PostMapping("/{sellerId}/apiKey")
    public ResponseEntity<GenerateApiKeyResponse> generateApiKey(
            @PathVariable String sellerId) {
        GenerateApiKeyResponse response = service.generateApiKey(sellerId);
        return ResponseEntity.ok(response);
    }
}
