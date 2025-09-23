package com.NextGenPay.service.sellerWebApp;

import com.NextGenPay.dto.response.GenerateApiKeyResponse;

public interface GenerateApiKeyService {
    GenerateApiKeyResponse generateApiKey(String sellerId);
}
