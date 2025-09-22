package com.NextGenPay.data.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;


@Setter
@Getter
@Document(collection="Customer Profile")
public class CustomerProfile {

    @Id
    private String id;

    private String customerId;

    private String nin;

    private String bvn;

    private String address;

    private LocalDate dateOfBirth;

    private String profileImage;



}
