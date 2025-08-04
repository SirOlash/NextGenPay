package com.NextGenPay.service;

import com.NextGenPay.data.model.SellerAdmin;
import com.NextGenPay.data.model.Wallet;
import com.NextGenPay.data.repository.SellerAdminRepository;
import com.NextGenPay.dto.request.GenerateApiKeyRequest;
import com.NextGenPay.dto.response.GenerateApiKeyResponse;
import com.NextGenPay.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;


@Service
@RequiredArgsConstructor
public class GenerateApiKeyServiceImpl implements GenerateApiKeyService {

    private final SellerAdminRepository sellerRepo;
    private final SecureRandom random = new SecureRandom();

    @Override
    public GenerateApiKeyResponse generateApiKey(String sellerId) {
        SellerAdmin admin = sellerRepo.findBySellerAdminId(sellerId)
                .orElseThrow(() -> new AccountNotFoundException("Account Not found!!!"));

        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        String apiKey = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

        admin.setApiKey(apiKey);
        sellerRepo.save(admin);

        return new GenerateApiKeyResponse(
                admin.getSellerAdminId(),
                apiKey,
                "Api key generated successfully"
        );
    }
}
