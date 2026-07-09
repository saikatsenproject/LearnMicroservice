package com.saikatsenportfolio.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    @NotEmpty(message = "Account Number field can't be empty")
    @Size(min = 1,max = 10,message = "Account Number field value should be between 1 to 10")
    @Digits(integer = 10,fraction = 0,message = "Account Number should be in digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type field can't be empty")
    @Size(min = 1,max = 20,message = "Account Type field value should be between 1 to 20")
    private String accountType;

    @NotEmpty(message = "Branch Address field can't be empty")
    @Size(min = 5,max = 100,message = "Branch Address field value should be between 5 to 100")
    private String branchAddress;
}
