package com.NextGenPay.service.customerMobileApp;
import com.NextGenPay.data.model.TransactionsHistory;
import com.NextGenPay.data.model.Wallet;
import com.NextGenPay.data.repository.TransactionsHistoryRepository;
import com.NextGenPay.data.repository.WalletRepository;
import com.NextGenPay.dto.request.AddFundsRequest;
import com.NextGenPay.dto.response.AddFundsResponse;
import com.NextGenPay.exception.AccountNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AddFundsServiceImpl implements AddFundsService {

    private final WalletRepository walletRepo;
    private final TransactionsHistoryRepository txnHistoryRepo;
    private final ObjectMapper objectMapper;

    @Override
    public AddFundsResponse addFunds(AddFundsRequest req) {
        Wallet wallet = walletRepo.findByAccountNumber(req.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found for account " + req.getAccountNumber()));

        double newBalance = wallet.getBalance() + req.getAmount();
        wallet.setBalance(newBalance);
        walletRepo.save(wallet);

        TransactionsHistory txn = objectMapper.convertValue(req, TransactionsHistory.class);
        TransactionsHistory savedTxn = txnHistoryRepo.save(txn);

        AddFundsResponse addFundsResponse = objectMapper.convertValue(savedTxn, AddFundsResponse.class);
        addFundsResponse.setMessage("Funds added successfully");
        return addFundsResponse;

    }
}
