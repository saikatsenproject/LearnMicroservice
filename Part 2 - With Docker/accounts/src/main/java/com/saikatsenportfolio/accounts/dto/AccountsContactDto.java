package com.saikatsenportfolio.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactDto(String message, HashMap<String,String> contactDetails, List<String> phNo) {
}
