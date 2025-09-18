package com.NextGenPay.data.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "DebitTransactionHistory")
public class DebitTransactionHistory {

    private String transactionId;
    private String cashierId;
    private String customerAccountNumber;
    private double amount;
    private PaymentType type;
    private LocalDate transactionDate;
    private TransactionStatus status;
    private String description;

}
