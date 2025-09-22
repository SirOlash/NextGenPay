package com.NextGenPay.controller;

import com.NextGenPay.dto.request.GenerateQrCodeRequest;
import com.NextGenPay.dto.response.GenerateQrCodeResponse;
import com.NextGenPay.service.sellerWebApp.GenerateQrCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qrcode")
public class GenerateQrController {
    private final GenerateQrCodeService qrService;

    @PostMapping
    public ResponseEntity<GenerateQrCodeResponse> generateQrCode(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody GenerateQrCodeRequest request) throws Exception {
        String apiKey = authHeader.replaceFirst("^ApiKey\\s+", "");
        GenerateQrCodeResponse response = qrService.generateQrCode(apiKey, request);
        return ResponseEntity.ok(response);
    }
}
