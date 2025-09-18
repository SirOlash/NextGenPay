package com.NextGenPay.data.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Cashier")
public class Cashier {
    @Id
    private String cashierId;
    private String userName;
    private String phoneNumber;
    private String accountNumber;
    private LocalDate dateRegistered;
    private String sellerAdminId;

}
