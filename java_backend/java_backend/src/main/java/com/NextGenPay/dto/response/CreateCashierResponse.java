package com.NextGenPay.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCashierResponse {

    private String message;
    private String cashierId;
    private String userName;
    private String sellerAdminId;
}

