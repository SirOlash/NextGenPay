package com.NextGenPay.service.customerMobileApp;
import com.NextGenPay.data.model.Wallet;
import com.NextGenPay.data.repository.WalletRepository;
import com.NextGenPay.dto.request.GenerateWalletRequest;
import com.NextGenPay.dto.response.GenerateWalletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
@RequiredArgsConstructor
public class GenerateWalletServiceImpl implements GenerateWalletServiceAuth {

    private final ObjectMapper objectMapper;
    private final WalletRepository walletRepo;
    private final SecureRandom random = new SecureRandom();

    @Override
    public GenerateWalletResponse generateWallet(GenerateWalletRequest request) {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        String accountNumber = sb.toString();

        Wallet wallet = objectMapper.convertValue(request, Wallet.class);
        wallet.setAccountNumber(accountNumber);
        wallet.setBalance(0.0);

        walletRepo.save(wallet);

        GenerateWalletResponse response = objectMapper.convertValue(wallet, GenerateWalletResponse.class);
        response.setMessage("Wallet Created Successfully");

        return response;

    }
}
