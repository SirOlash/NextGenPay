package com.NextGenPay.service.customerMobileApp;
import com.NextGenPay.data.model.Customer;
import com.NextGenPay.data.model.CustomerProfile;
import com.NextGenPay.data.repository.CustomerProfileRepo;
import com.NextGenPay.data.repository.CustomerRepo;
import com.NextGenPay.dto.response.AddFundsResponse;
import com.NextGenPay.exception.CustomerNotFoundException;
import com.NextGenPay.exception.UserNameAlreadyExistException;
import com.NextGenPay.dto.request.CreateProfileRequest;
import com.NextGenPay.dto.response.CreateProfileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final ObjectMapper objectMapper;

    @Autowired
    private CustomerProfileRepo customerProfileRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CreateProfileResponse createProfile(CreateProfileRequest createProfileRequest) {
        Customer foundCustomer = getCustomer(createProfileRequest.getCustomerId());
        CustomerProfile customerProfile = setCustomerProfile(createProfileRequest, foundCustomer);

        CustomerProfile savedProfile = customerProfileRepo.save(customerProfile);

        foundCustomer.setProfileId(savedProfile.getId());
        customerRepo.save(foundCustomer);

        String message = "Profile created successfully";
        return new CreateProfileResponse(message, savedProfile.getId());
    }

    @Override
    public CreateProfileResponse updateProfile(CreateProfileRequest createProfileRequest) {
        Customer newFoundCustomer = getCustomer(createProfileRequest.getCustomerId());
        CustomerProfile customerProfile = setCustomerProfile(createProfileRequest, newFoundCustomer);

        CustomerProfile savedProfile = customerProfileRepo.save(customerProfile);

        newFoundCustomer.setProfileId(savedProfile.getId());
        customerRepo.save(newFoundCustomer);

        String message = "Profile updated successfully";
        return new CreateProfileResponse(message, savedProfile.getId());
    }

    private static CustomerProfile setCustomerProfile(CreateProfileRequest createProfileRequest, Customer newFoundCustomer) {
        CustomerProfile customerProfile = new CustomerProfile();

//        customerProfile.setProfileImage(createProfileRequest.getProfileImage());
        customerProfile.setAddress(createProfileRequest.getAddress());
        customerProfile.setDateOfBirth(createProfileRequest.getDateOfBirth());
        customerProfile.setNin(createProfileRequest.getNin());
        customerProfile.setBvn(createProfileRequest.getBvn());
        customerProfile.setCustomerId(newFoundCustomer.getCustomerId());
        return customerProfile;
    }

    private Customer getCustomer(String customerId){
        Customer customer = customerRepo.findCustomerByCustomerId(customerId);
        if(customer == null){throw new CustomerNotFoundException("Customer not found");}
        return customer;
    }
}
