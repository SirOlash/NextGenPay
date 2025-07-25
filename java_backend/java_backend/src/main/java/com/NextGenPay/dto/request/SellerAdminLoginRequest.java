package com.NextGenPay.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerAdminLoginRequest {

    @NotNull(message="This field is required")
    private String email;
    @NotNull(message="This field is required")
    private String password;

}
