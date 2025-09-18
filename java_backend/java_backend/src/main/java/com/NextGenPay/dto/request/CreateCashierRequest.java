package com.NextGenPay.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCashierRequest {

    @NotNull(message = "This field is required")
    private String userName;
    @NotNull(message = "This field is required")
    private String phoneNumber;
    @NotBlank(message = "This field is required")
    private String accountNumber;
    @NotNull(message = "This field is required")
    private LocalDate dateRegistered;


}
