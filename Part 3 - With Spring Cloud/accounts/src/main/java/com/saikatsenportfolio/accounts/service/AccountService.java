package com.saikatsenportfolio.accounts.service;

import com.saikatsenportfolio.accounts.constant.AccountConstant;
import com.saikatsenportfolio.accounts.constant.CustomerConstant;
import com.saikatsenportfolio.accounts.dto.AccountDto;
import com.saikatsenportfolio.accounts.dto.CustomerDto;
import com.saikatsenportfolio.accounts.dto.ResponseDto;
import com.saikatsenportfolio.accounts.entity.Accounts;
import com.saikatsenportfolio.accounts.entity.Customers;
import com.saikatsenportfolio.accounts.exception.AccountException;
import com.saikatsenportfolio.accounts.exception.CustomerException;
import com.saikatsenportfolio.accounts.mapper.AccountDtoMapper;
import com.saikatsenportfolio.accounts.mapper.CustomerDtoMapper;
import com.saikatsenportfolio.accounts.repository.AccountRepository;
import com.saikatsenportfolio.accounts.repository.CustomerRepository;
import com.saikatsenportfolio.accounts.service.impl.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    @Override
    public ResponseEntity<ResponseDto> createCustomer(CustomerDto customerDto) {
        String email=customerDto.getEmail();
        Optional<Customers> listCustomers=customerRepository.findByEmail(email);
        if(listCustomers.isPresent()){
            throw new CustomerException(CustomerConstant.ERROR_CUSTOMER_EMAIL_MSG);
        }
        Customers customer= CustomerDtoMapper.customerDtoToCustomer(customerDto,new Customers());
//        customer.setCreatedBy("Saikat Sen");
//        customer.setCreatedAt(LocalDate.now());
        customer=customerRepository.saveAndFlush(customer);
        Long customerId=customer.getCustomerId();
        Accounts accounts=new Accounts();
        //Long accNo=(long)(customerId+(Math.random()*1000));
        String accType= AccountConstant.ACCOUNT_TYPE_SAVINGS;
        String accAddress=AccountConstant.ACCOUNT_BRANCH_ADDRESS;
        accounts.setCustomerId(customerId);
        accounts.setAccountType(accType);
        accounts.setBranchAddress(accAddress);
        accounts.setCreatedBy(customer.getCreatedBy());
        accounts.setCreatedAt(customer.getCreatedAt());
        accountRepository.saveAndFlush(accounts);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CustomerConstant.SUCCESS_STATUS,CustomerConstant.SUCCESS_MSG));
    }
    @Override
    public ResponseEntity<CustomerDto> findCustomer(String email) {
        Optional<Customers> listCustomers=customerRepository.findByEmail(email);
        if(!listCustomers.isPresent()){
            throw new CustomerException(CustomerConstant.ERROR_NO_CUSTOMER_EMAIL_MSG);
        }
        CustomerDto customerDto= CustomerDtoMapper.customerToCustomerDto(listCustomers.get(),new CustomerDto());
        Optional<Accounts> listAccounts=accountRepository.findByCustomerId(listCustomers.get().getCustomerId());
        if(!listAccounts.isPresent()){
            throw new CustomerException(AccountConstant.ERROR_NO_ACCOUNT_EMAIL_MSG);
        }
        AccountDto accountDto= AccountDtoMapper.accountToAccountDto(listAccounts.get(),new AccountDto());
        customerDto.setAccountDto(accountDto);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Override
    public ResponseEntity<Boolean> updateAccount(CustomerDto customerDto) {
        AccountDto accountDto=customerDto.getAccountDto();
        Long accNo=accountDto.getAccountNumber();
        Long custId;
        Optional<Accounts> listAccounts=accountRepository.findById(accNo);
        if(!listAccounts.isPresent()){
            throw new AccountException(AccountConstant.ERROR_NO_ACCOUNT_FOUND_WITH_ACC_NO,String.valueOf(accNo));
        }
        custId=listAccounts.get().getCustomerId();
        Optional<Customers> listCustomers=customerRepository.findById(custId);
        if(!listCustomers.isPresent()){
            throw new CustomerException(CustomerConstant.ERROR_NO_CUSTOMER_MSG);
        }
        Customers customer=CustomerDtoMapper.customerDtoToCustomer(customerDto,listCustomers.get());
        Accounts account=AccountDtoMapper.accountDtoToAccount(accountDto,listAccounts.get());
        accountRepository.saveAndFlush(account);
        customerRepository.saveAndFlush(customer);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @Override
    public ResponseEntity<Boolean> deleteAccount(@RequestParam(value="email") String email) {
        Long custId;
        Optional<Customers> listCustomers=customerRepository.findByEmail(email);
        if(!listCustomers.isPresent()){
            throw new CustomerException(CustomerConstant.ERROR_NO_CUSTOMER_MSG);
        }
        custId=listCustomers.get().getCustomerId();
        customerRepository.deleteById(custId);
        accountRepository.deleteByCustomerId(custId);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
