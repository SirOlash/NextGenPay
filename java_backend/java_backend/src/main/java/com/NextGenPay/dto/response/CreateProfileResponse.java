package com.NextGenPay.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProfileResponse {

    private String message;
    private String profileId;
    private String userName;

}
