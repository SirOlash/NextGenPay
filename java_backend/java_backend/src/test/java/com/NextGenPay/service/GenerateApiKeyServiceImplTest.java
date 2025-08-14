//package com.NextGenPay.service;
//
//import com.NextGenPay.data.model.SellerAdmin;
//import com.NextGenPay.data.repository.SellerAdminRepository;
//import com.NextGenPay.dto.request.GenerateApiKeyRequest;
//import com.NextGenPay.dto.response.GenerateApiKeyResponse;
//import com.NextGenPay.exception.AccountNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class GenerateApiKeyServiceImplTest {
//
//    @Autowired
//    private SellerAdminRepository sellerRepo;
//
//    @Autowired
//    private GenerateApiKeyService service;
//
////    @BeforeEach
////    void setUp() {
////        sellerRepo.deleteAll();
////
////        SellerAdmin admin = new SellerAdmin();
////        admin.setSellerAdminId("seller-123");
////        admin.setEmail("seller@gmail.com");
////        sellerRepo.save(admin);
////    }
//
//    @Test
//    void generateApiKey_shouldAssignApiKeyAndReturnResponse() {
//
//        GenerateApiKeyResponse response = service.generateApiKey("seller-123");
//
//        assertThat(response).isNotNull();
//        assertThat(response.getApiKey().length()).isGreaterThanOrEqualTo(32);
//
//
//        List<SellerAdmin> all = sellerRepo.findAll();
//        assertThat(all).hasSize(1);
//        SellerAdmin updated = all.get(0);
//        assertThat(updated.getSellerAdminId()).isEqualTo("seller-123");
//        assertThat(updated.getApiKey()).isEqualTo(response.getApiKey());
//    }
//
//    @Test
//    void generateApiKey_withUnknownSeller_throwsException() {
//        assertThatThrownBy(() -> service.generateApiKey("unknown"))
//                .isInstanceOf(AccountNotFoundException.class)
//                .hasMessageContaining("Account Not found!!!");
//    }
//}