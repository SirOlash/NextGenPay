package com.NextGenPay.dto.response;

import com.NextGenPay.data.model.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerProfileResponse {
    private String sellerAdminId;
    private String firstName;
    private String lastName;
    private String email;
    private String businessName;
    BusinessType businessType;
    private String apiKey;
}
