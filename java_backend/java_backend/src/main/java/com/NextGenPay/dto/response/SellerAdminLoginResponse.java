package com.NextGenPay.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerAdminLoginResponse {

    private String message;
    private String sellerAdminId;
    private String token;

}
