package com.NextGenPay.service.customerMobileApp;
import com.NextGenPay.dto.request.CreateProfileRequest;
import com.NextGenPay.dto.response.CreateProfileResponse;
import org.springframework.stereotype.Service;


@Service
public interface CustomerProfileService {

    CreateProfileResponse createProfile(CreateProfileRequest createProfileRequest);
    CreateProfileResponse updateProfile(CreateProfileRequest createProfileRequest);

}
