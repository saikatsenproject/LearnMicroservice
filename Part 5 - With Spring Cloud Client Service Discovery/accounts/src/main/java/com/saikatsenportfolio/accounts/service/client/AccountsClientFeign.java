package com.saikatsenportfolio.accounts.service.client;

import com.saikatsenportfolio.accounts.dto.CustomerDto;
import jakarta.validation.constraints.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "accounts")
public interface AccountsClientFeign {

    @GetMapping("/v1/account/find")
    ResponseEntity<CustomerDto> findUser(@RequestParam String email);
}
