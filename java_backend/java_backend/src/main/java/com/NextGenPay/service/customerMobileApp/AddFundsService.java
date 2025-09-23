package com.NextGenPay.service.customerMobileApp;

import com.NextGenPay.dto.request.AddFundsRequest;
import com.NextGenPay.dto.response.AddFundsResponse;

public interface AddFundsService {
    AddFundsResponse addFunds(AddFundsRequest request);
}
