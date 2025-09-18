package com.NextGenPay.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateQrCodeRequest {
    @NotBlank(message = "Field cannot be empty" )
    private String cashierId;

    @NotBlank(message = "Field cannot be empty")


    @NotNull(message = "Field cannot be empty") @DecimalMin("0.01")
    private BigDecimal amount;


}
