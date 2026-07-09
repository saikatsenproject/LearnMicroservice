package com.saikatsenportfolio.accounts.service.impl;

import com.saikatsenportfolio.accounts.dto.CustomerDto;
import com.saikatsenportfolio.accounts.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface IAccountService {
    ResponseEntity<ResponseDto> createCustomer(CustomerDto customerDto);
    ResponseEntity<CustomerDto> findCustomer(String email);
    ResponseEntity<Boolean> updateAccount(CustomerDto customerDto);
    ResponseEntity<Boolean> deleteAccount(@RequestParam(value="email") String email);
}
