//package com.NextGenPay.controller;
//import com.NextGenPay.data.model.Wallet;
//import com.NextGenPay.data.repository.WalletRepository;
//import com.NextGenPay.dto.request.GenerateWalletRequest;
//import com.NextGenPay.dto.response.GenerateWalletResponse;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import static org.hamcrest.Matchers.hasLength;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
////@WebMvcTest(GenerateWalletController.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//class GenerateWalletControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private WalletRepository walletRepo;
//
//    @BeforeEach
//    void cleanUp() {
//        walletRepo.deleteAll();
//    }
//
//    @Test
//    void postGenerateWallet_returns200_andResponseBody() throws Exception {
//        GenerateWalletRequest request = new GenerateWalletRequest("12345678");
//
//        String jsonRequest = objectMapper.writeValueAsString(request);
//        String jsonResponse = mockMvc.perform(post("/api/wallets")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonRequest))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.balance").value(0))
//                .andExpect(jsonPath("$.accountNumber").isString())
//                .andExpect(jsonPath("$.accountNumber", hasLength(10)))
//                .andExpect(jsonPath("$.message").value("Wallet Created Successfully"))
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        GenerateWalletResponse response = objectMapper.readValue(jsonResponse, GenerateWalletResponse.class);
//        List<Wallet> saved = walletRepo.findAll();
//        assertThat(saved).hasSize(1);
//
//        Wallet w = saved.get(0);
//        assertThat(w.getCustomerId()).isEqualTo("12345678");
//        assertThat(w.getBalance()).isEqualByComparingTo(0.0);
//        assertThat(w.getAccountNumber()).isEqualTo(response.getAccountNumber());
//
//    }
//}