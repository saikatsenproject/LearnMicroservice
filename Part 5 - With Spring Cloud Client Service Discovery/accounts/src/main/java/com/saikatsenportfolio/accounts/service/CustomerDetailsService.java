package com.saikatsenportfolio.accounts.service;

import com.saikatsenportfolio.accounts.constant.AccountConstant;
import com.saikatsenportfolio.accounts.constant.CustomerConstant;
import com.saikatsenportfolio.accounts.dto.AccountDto;
import com.saikatsenportfolio.accounts.dto.CardDto;
import com.saikatsenportfolio.accounts.dto.CustomerDetailsDto;
import com.saikatsenportfolio.accounts.entity.Accounts;
import com.saikatsenportfolio.accounts.entity.Customers;
import com.saikatsenportfolio.accounts.exception.CustomerException;
import com.saikatsenportfolio.accounts.mapper.AccountDtoMapper;
import com.saikatsenportfolio.accounts.repository.AccountRepository;
import com.saikatsenportfolio.accounts.repository.CustomerRepository;
import com.saikatsenportfolio.accounts.service.client.CardsClientFeign;
import com.saikatsenportfolio.accounts.service.impl.ICustomerDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerDetailsService implements ICustomerDetailsService {
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private CardsClientFeign cardsClientFeign;
    @Override
    public ResponseEntity<CustomerDetailsDto> findCustomerDetails(String email) {
        CustomerDetailsDto customerDetailsDto=new CustomerDetailsDto();
        Optional<Customers> listCustomers=customerRepository.findByEmail(email);
        if(!listCustomers.isPresent()){
            throw new CustomerException(CustomerConstant.ERROR_NO_CUSTOMER_EMAIL_MSG);
        }
        BeanUtils.copyProperties(listCustomers.get(),customerDetailsDto);
        Optional<Accounts> listAccounts=accountRepository.findByCustomerId(listCustomers.get().getCustomerId());
        if(!listAccounts.isPresent()){
            throw new CustomerException(AccountConstant.ERROR_NO_ACCOUNT_EMAIL_MSG);
        }
        AccountDto accountDto= AccountDtoMapper.accountToAccountDto(listAccounts.get(),new AccountDto());
        customerDetailsDto.setAccountDto(accountDto);
        ResponseEntity<CardDto> cardDtoResponseEntity=cardsClientFeign.findCard(email);
        if(cardDtoResponseEntity.hasBody()){
            customerDetailsDto.setCardDto(cardDtoResponseEntity.getBody());
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
