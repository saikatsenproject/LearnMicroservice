package com.saikatsenportfolio.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    @NotEmpty(message = "Email field can't be empty")
    @Email(message = "Please provide valid Email Id")
    private String emailId;

    @NotEmpty(message = "Card Number field can't be empty")
    @Size(min = 1,max = 10,message = "Card Number field value should be between 1 to 10")
    @Digits(integer = 10,fraction = 0,message = "Card Number should be in digits")
    private String cardNumber;

    @NotEmpty(message = "Card Type field can't be empty")
    private String cardType;

    @NotNull(message = "Total limit field can't be empty")
    @Positive(message = "Total Limit should be greater than zero")
    private Long totalLimit;

    @NotNull(message = "Amount Used field can't be empty")
    @PositiveOrZero(message = "Amount Used should be greater than or equals zero")
    private Long amountUsed;

    @NotNull(message = "Available Amount field can't be empty")
    @PositiveOrZero(message = "Available Amount should be greater than or equals zero")
    private Long availableAmount;
}
