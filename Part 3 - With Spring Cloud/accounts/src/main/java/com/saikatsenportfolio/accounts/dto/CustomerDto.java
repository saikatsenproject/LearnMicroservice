package com.saikatsenportfolio.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotEmpty(message = "Name field can't be empty")
    @Size(min = 1, max = 20, message = "Name field value should be between 1 to 20")
    private String name;

    @NotEmpty(message = "Email field can't be empty")
    @Email(message = "Please provide valid Email Id")
    private String email;

    @NotEmpty(message = "Mobile Number field can't be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Number should be 10 digits")
    private String mobileNumber;

    private AccountDto accountDto;
}
