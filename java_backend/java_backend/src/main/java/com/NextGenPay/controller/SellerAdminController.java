package com.NextGenPay.controller;
import com.NextGenPay.dto.request.CreateCashierRequest;
import com.NextGenPay.dto.request.SellerAdminLoginRequest;
import com.NextGenPay.dto.request.SellerAdminRegisterRequest;
import com.NextGenPay.dto.response.*;
import com.NextGenPay.service.SellerAdminService;
import com.NextGenPay.service.SellerAdminServiceImpl;
import com.NextGenPay.service.SellerProfileService;
//import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;


@RestController
@RequestMapping("/api/v1/seller-admin")
@AllArgsConstructor
public class SellerAdminController {

    private final SellerProfileService profileService;
    private final SellerAdminServiceImpl adminService;

    @Autowired
    SellerAdminServiceImpl service;

    @PostMapping("/register/admin")
    public ResponseEntity<SellerAdminRegisterResponse> registerSellerAdmin(@RequestBody @Valid SellerAdminRegisterRequest request){
        return ResponseEntity.ok(service.registerSellerAdmin(request));
    }

    @PostMapping("/login/admin")
    public ResponseEntity<SellerAdminLoginResponse> loginSellerAdmin(@RequestBody @Valid SellerAdminLoginRequest request){
        return ResponseEntity.ok(service.loginSellerAdmin(request));
    }

    @PostMapping("/{sellerId}/create-cashier")
    public ResponseEntity<CreateCashierResponse> createCashier(
            @PathVariable("sellerId") String sellerId,
            @RequestBody @Valid CreateCashierRequest request){
        CreateCashierResponse response = service.createCashier(sellerId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{sellerAdminId}/view-adminProfile")
    public ResponseEntity<SellerProfileResponse> getProfile(@PathVariable String sellerAdminId) {
//        String sellerAdminEmail = jwt.getSubject();  // 'sub' claim
        SellerProfileResponse resp = profileService.getSellerProfile(sellerAdminId);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{sellerId}/cashiers")
    public ResponseEntity<List<CashierResponse>> listCashiers(
            @PathVariable String sellerId
    ) {
        List<CashierResponse> dtos = adminService.getCashiers(sellerId);
        return ResponseEntity.ok(dtos);
    }

}
