package com.NextGenPay.service.sellerWebApp;

import com.NextGenPay.data.model.SellerAdmin;
import com.NextGenPay.data.repository.SellerAdminRepository;
import com.NextGenPay.dto.response.SellerProfileResponse;
import com.NextGenPay.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerProfileService {

    private final SellerAdminRepository adminRepository;

    public SellerProfileResponse getSellerProfile(String sellerAdminId) {
        SellerAdmin sellerAdmin = adminRepository
                .findBySellerAdminId(sellerAdminId)
                .orElseThrow(() -> new AccountNotFoundException(
                        "No SellerAdmin with id " + sellerAdminId));

        if (sellerAdmin == null) {
            throw new IllegalArgumentException("Profile not found for seller: ");
        }
        return SellerProfileResponse.builder()
                .sellerAdminId(sellerAdmin.getSellerAdminId())
                .firstName(sellerAdmin.getFirstName())
                .lastName(sellerAdmin.getLastName())
                .email(sellerAdmin.getEmail())
                .businessName(sellerAdmin.getBusinessName())
                .businessType(sellerAdmin.getBusinessType())
                .apiKey(sellerAdmin.getApiKey())
                .build();
    }

}
