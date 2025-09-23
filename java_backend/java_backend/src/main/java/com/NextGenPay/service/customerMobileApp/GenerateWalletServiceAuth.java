package com.NextGenPay.service.customerMobileApp;
import com.NextGenPay.dto.request.GenerateWalletRequest;
import com.NextGenPay.dto.response.GenerateWalletResponse;
import org.springframework.stereotype.Service;

@Service
public interface GenerateWalletServiceAuth {
    GenerateWalletResponse generateWallet(GenerateWalletRequest request);
}
