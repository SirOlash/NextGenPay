package com.NextGenPay.service;

import com.NextGenPay.dto.request.GenerateApiKeyRequest;
import com.NextGenPay.dto.response.GenerateApiKeyResponse;

public interface GenerateApiKeyService {
    GenerateApiKeyResponse generateApiKey(String sellerId);
}
