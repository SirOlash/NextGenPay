package com.NextGenPay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashierResponse {
    private String      id;
    private String      userName;
    private String      phoneNumber;
    private LocalDate dateRegistered;
}
