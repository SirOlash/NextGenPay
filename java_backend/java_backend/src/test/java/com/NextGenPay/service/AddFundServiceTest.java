//package com.NextGenPay.service;
//import com.NextGenPay.data.model.Wallet;
//import com.NextGenPay.data.repository.TransactionsHistoryRepository;
//import com.NextGenPay.data.repository.WalletRepository;
//import com.NextGenPay.dto.request.AddFundsRequest;
//import com.NextGenPay.dto.response.AddFundsResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
////import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest
//public class AddFundServiceTest {
//
//    @Autowired
//    private WalletRepository walletRepository;
//
//    @Autowired
//    private TransactionsHistoryRepository txnHistoryRepo;
//
//    @Autowired
//    private AddFundsService service;
//
//    @BeforeEach
//    void setUp() {
//        txnHistoryRepo.deleteAll();
//        walletRepository.deleteAll();
//
//        Wallet wallet = new Wallet();
//        wallet.setCustomerId("custo-12345");
//        wallet.setAccountNumber("123456789");
//        wallet.setBalance(0.0);
//        walletRepository.save(wallet);
//    }
//
//    @Test
//    void addFunds_shouldCreditWallet_andCreateTransaction(){
//        AddFundsRequest request = new AddFundsRequest("123456789", (2000.00));
//
//        AddFundsResponse response = service.addFunds(request);
//
//        Wallet updated = walletRepository.findByAccountNumber("123456789").get();
//        assertThat(updated.getBalance()).isEqualByComparingTo(2000.00);
//
//        List<?> txns = txnHistoryRepo.findAll();
//        assertThat(txns).hasSize(1);
//
//        assertThat(response.getTransactionId()).isNotEmpty();
//        assertThat(response.getAmount()).isEqualByComparingTo(new BigDecimal("2000.00"));
//        assertThat(response.getMessage()).isEqualTo("Funds added successfully");
//        assertThat(response.getDateTransferred()).isNotNull();
//    }
//}
