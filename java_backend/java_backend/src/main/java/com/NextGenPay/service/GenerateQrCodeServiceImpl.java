package com.NextGenPay.service;

import com.NextGenPay.data.model.Cashier;
import com.NextGenPay.data.model.Payload;
import com.NextGenPay.data.model.SellerAdmin;
import com.NextGenPay.data.repository.CashierRepo;
import com.NextGenPay.data.repository.SellerAdminRepository;
import com.NextGenPay.dto.request.GenerateQrCodeRequest;
import com.NextGenPay.dto.response.GenerateQrCodeResponse;
import com.NextGenPay.exception.AccountNotFoundException;
import com.NextGenPay.exception.CashierNotManagedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class GenerateQrCodeServiceImpl implements  GenerateQrCodeService {
    private final SellerAdminRepository sellerRepo;
    private final ObjectMapper objectMapper;
    private final CashierRepo cashierRepo;

    @Override
    public GenerateQrCodeResponse generateQrCode(String apiKey, GenerateQrCodeRequest request) throws Exception {
        SellerAdmin sellerAdmin = sellerRepo.findByApiKey(apiKey)
                .orElseThrow(() -> new AccountNotFoundException("Invalid API Key"));

        Cashier cashier = cashierRepo.findById(request.getCashierId())
                .orElseThrow(() -> new AccountNotFoundException("Invalid Cashier Id"));

        if (!sellerAdmin.getSellerAdminId().contains(cashier.getSellerAdminId())) {
            throw new CashierNotManagedException("Cashier not managed by this seller");
        }

        Payload payload = new Payload(
                request.getCashierId(),
                request.getAmount().toString(),
                Instant.now().toString()
        );
        String json = objectMapper.writeValueAsString(payload);

        BitMatrix matrix = new MultiFormatWriter()
                .encode(json, BarcodeFormat.QR_CODE, 250, 250);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", baos);

        String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
        Instant expires = Instant.now().plusSeconds(300);

        return new GenerateQrCodeResponse(base64, expires);
    }


}


