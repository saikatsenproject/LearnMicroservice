package com.saikatsenportfolio.accounts.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "accounts")
//@Getter
//@Setter
@Data
public class AccountsContactDto{
    private String message;
    private HashMap<String,String> contactDetails;
    private List<String> phNo;
}
