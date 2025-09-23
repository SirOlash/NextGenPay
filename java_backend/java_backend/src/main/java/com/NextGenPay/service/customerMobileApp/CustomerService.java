package com.NextGenPay.service.customerMobileApp;
import com.NextGenPay.data.model.DebitTransactionHistory;
import com.NextGenPay.data.model.Wallet;
import com.NextGenPay.dto.request.ScanToPayRequest;



public interface CustomerService {

    DebitTransactionHistory scanToPay(ScanToPayRequest request);
    Wallet displayWallet(String customerId);

}
