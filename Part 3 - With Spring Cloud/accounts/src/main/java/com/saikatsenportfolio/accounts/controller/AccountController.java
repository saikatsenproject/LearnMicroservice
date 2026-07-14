package com.saikatsenportfolio.accounts.controller;

import com.saikatsenportfolio.accounts.dto.AccountsContactDto;
import com.saikatsenportfolio.accounts.dto.CustomerDto;
import com.saikatsenportfolio.accounts.dto.ResponseDto;
import com.saikatsenportfolio.accounts.entity.Customers;
import com.saikatsenportfolio.accounts.repository.CustomerRepository;
import com.saikatsenportfolio.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

// this @Validated is optional which tells our spring boot application that we need to validated all @Valid in controller ,
// this is mandatory only when we use it with @RequestParam

@Tag(
        name = "Account Controller for CRUD API"
)
@RestController
@RequiredArgsConstructor
@Validated
public class AccountController {
    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final AccountsContactDto accountsContactDto;

    @Value("${build.version}")
    private String buildVersion;

    @GetMapping("/build-info")
    ResponseEntity<String> getBuildVersion(){
        return ResponseEntity.ok(buildVersion);
    }
    @GetMapping("/config")
    ResponseEntity<AccountsContactDto> getContactInfo(){
        return ResponseEntity.ok(accountsContactDto);
    }
    @GetMapping("/h2/data")
    ResponseEntity<List<Customers>> getAllCustData(){
        try{
            List<Customers> list=customerRepository.findAll();
            return ResponseEntity.ok(list);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }
    @Operation(
            summary = "Create Customer and Account API"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Customer and Account Details Created"
    )
    @PostMapping("/v1/account/create")
    ResponseEntity<ResponseDto> createUser(@Valid @RequestBody CustomerDto customerDto){
        String email= customerDto.getEmail();
        String mobileNumber=customerDto.getMobileNumber();
        return accountService.createCustomer(customerDto);
    }
    @GetMapping("/v1/account/find")
    ResponseEntity<CustomerDto> findUser(@RequestParam(value = "email") @Email(message = "Please provide valid Email Id") String email){
        return accountService.findCustomer(email);
    }
    @PutMapping("/v1/account/update")
    ResponseEntity<Boolean> updateUserAccount(@Valid @RequestBody CustomerDto customerDto){
        return accountService.updateAccount(customerDto);
    }
    @DeleteMapping("/v1/account/delete")
    ResponseEntity<Boolean> deleteUserAccount(@RequestParam(value = "email") @Email(message = "Please provide valid Email Id") String email){
        return accountService.deleteAccount(email);
    }
}
