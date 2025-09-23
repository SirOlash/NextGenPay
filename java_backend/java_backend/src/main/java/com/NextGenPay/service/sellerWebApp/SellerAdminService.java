package com.NextGenPay.service.sellerWebApp;
import com.NextGenPay.dto.request.CreateCashierRequest;
import com.NextGenPay.dto.request.SellerAdminLoginRequest;
import com.NextGenPay.dto.request.SellerAdminRegisterRequest;
import com.NextGenPay.dto.response.CreateCashierResponse;
import com.NextGenPay.dto.response.SellerAdminLoginResponse;
import com.NextGenPay.dto.response.SellerAdminRegisterResponse;
import org.springframework.stereotype.Service;


@Service
public interface SellerAdminService {

    public SellerAdminRegisterResponse registerSellerAdmin(SellerAdminRegisterRequest request);
    public SellerAdminLoginResponse loginSellerAdmin(SellerAdminLoginRequest request);
    public CreateCashierResponse createCashier(String sellerId, CreateCashierRequest request);

}
