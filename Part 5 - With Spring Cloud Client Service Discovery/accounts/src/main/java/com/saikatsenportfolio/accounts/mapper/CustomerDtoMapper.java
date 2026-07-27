package com.saikatsenportfolio.accounts.mapper;

import com.saikatsenportfolio.accounts.dto.CustomerDto;
import com.saikatsenportfolio.accounts.entity.Customers;
import org.springframework.beans.BeanUtils;

public class CustomerDtoMapper {

    public static Customers customerDtoToCustomer(CustomerDto customerDto,Customers customer){
        BeanUtils.copyProperties(customerDto,customer);
        return customer;
    }
    public static CustomerDto customerToCustomerDto(Customers customer,CustomerDto customerDto){
        BeanUtils.copyProperties(customer,customerDto);
        return customerDto;
    }
}
