package com.saikatsenportfolio.accounts.service.impl;

import com.saikatsenportfolio.accounts.dto.CustomerDetailsDto;
import org.springframework.http.ResponseEntity;

public interface ICustomerDetailsService {
    public ResponseEntity<CustomerDetailsDto> findCustomerDetails(String email);
}
