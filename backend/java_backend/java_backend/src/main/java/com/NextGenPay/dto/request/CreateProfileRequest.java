package com.NextGenPay.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileRequest {

    @NotNull(message = "This field is required")
    private String firstName;

    @NotNull(message = "This field is required")
    private String lastName;

    @NotNull(message ="This field is required")
    private String address;

    @NotNull(message = "This field is required")
    private String userName;

    @NotNull(message = "This field is required")
    private LocalDate dateOfBirth;

    private String profileImage;

    @NotNull(message = "This field is required")
    private String customerId;



}
