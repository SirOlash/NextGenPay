//package com.NextGenPay.service;
//
//import com.NextGenPay.data.model.Cashier;
//import com.NextGenPay.data.model.SellerAdmin;
//import com.NextGenPay.data.repository.CashierRepo;
//import com.NextGenPay.data.repository.SellerAdminRepository;
//import com.NextGenPay.dto.request.GenerateQrCodeRequest;
//import com.NextGenPay.dto.response.GenerateQrCodeResponse;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.BinaryBitmap;
//import com.google.zxing.LuminanceSource;
//import com.google.zxing.MultiFormatReader;
//import com.google.zxing.Result;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.util.Base64;
//import java.util.List;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class GenerateQrCodeServiceImplTest {
//    @Autowired
//    private SellerAdminRepository sellerRepo;
//
//    @Autowired
//    private GenerateQrCodeServiceImpl qrCodeService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private String apiKey;
////    private String cashierId = "cashier-001";
//    @Autowired
//    private CashierRepo cashierRepo;
//
//    @BeforeEach
//    void setUp() {
//        sellerRepo.deleteAll();
//
//        SellerAdmin admin = new SellerAdmin();
//        admin.setSellerAdminId("seller-123");
//        apiKey = "test-api-key-xyz";
//        admin.setApiKey(apiKey);
//        sellerRepo.save(admin);
//
//        Cashier cashier = new Cashier();
//        cashier.setSellerAdminId(admin.getSellerAdminId());
//        cashier.setCashierId("cashier-123");
//        cashier.setUserName("cashier");
////        cashier.setAccountNumber("1234567890");
//        cashierRepo.save(cashier);
//    }
//
//    @Test
//    void generateQr_returnsValidBase64PngContainingCorrectPayload() throws Exception {
//        BigDecimal amount = new BigDecimal("5000.00");
//        GenerateQrCodeRequest request = new GenerateQrCodeRequest("cashier-123", amount);
//        GenerateQrCodeResponse response = qrCodeService.generateQrCode(apiKey, request);
//
//        System.out.println(response);
//
//        assertThat(response).isNotNull();
//        assertThat(response.getQrCodeBase64()).isNotBlank();
//        assertThat(response.getExpiresAt()).isAfter(Instant.now());
//
//        byte[] png = Base64.getDecoder().decode(response.getQrCodeBase64());
//
//        BufferedImage img = ImageIO.read(new ByteArrayInputStream(png));
//        assertThat(img).isNotNull();
//
//        LuminanceSource source = new BufferedImageLuminanceSource(img);
//        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//        Result result = new MultiFormatReader().decode(bitmap);
//
//        String json = result.getText();
//        var map = objectMapper.readValue(json, java.util.Map.class);
////        Map <String,String> map = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
//
//        assertThat(map.get("cashierId")).isEqualTo("cashier-123");
//        assertThat(new BigDecimal((String)map.get("amount"))).isEqualByComparingTo(amount);
//        assertThat(new BigDecimal((String)map.get("accountNumber"))).isEqualByComparingTo("1234567890");
////        assertThat(map).containsKey("timestamp");
//    }
//
//}