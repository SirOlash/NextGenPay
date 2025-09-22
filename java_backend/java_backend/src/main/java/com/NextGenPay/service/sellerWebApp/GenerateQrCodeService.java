package com.NextGenPay.service.sellerWebApp;
import com.NextGenPay.dto.request.GenerateQrCodeRequest;
import com.NextGenPay.dto.response.GenerateQrCodeResponse;


public interface GenerateQrCodeService {
    GenerateQrCodeResponse generateQrCode(String apiKey, GenerateQrCodeRequest request) throws Exception;
}
