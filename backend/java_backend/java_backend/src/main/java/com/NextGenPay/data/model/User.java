package com.NextGenPay.data.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Document(collection="User")
public class User {
    @Id
    private String Id;

    @NotNull("This field is required")
    private String email;

    @NotNull("")
    private String password;
    private String phoneNumber;
    private LocalDateTime createAt;
    private LocalDateTime lastLogin;

}
