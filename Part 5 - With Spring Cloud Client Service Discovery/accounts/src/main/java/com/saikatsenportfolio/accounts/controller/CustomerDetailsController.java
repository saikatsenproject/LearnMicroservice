package com.saikatsenportfolio.accounts.controller;

import com.saikatsenportfolio.accounts.dto.CustomerDetailsDto;
import com.saikatsenportfolio.accounts.service.CustomerDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerDetailsController {
    private final CustomerDetailsService customerDetailsService;

    @GetMapping("/v1/accounts/customer-details")
    ResponseEntity<CustomerDetailsDto> getCustomerDetails(@RequestParam String email){
        return customerDetailsService.findCustomerDetails(email);
    }
}
