package com.saikatsenportfolio.accounts.mapper;

import com.saikatsenportfolio.accounts.dto.AccountDto;
import com.saikatsenportfolio.accounts.entity.Accounts;
import org.springframework.beans.BeanUtils;

public class AccountDtoMapper {
    public static Accounts accountDtoToAccount(AccountDto accountDto, Accounts account){
        BeanUtils.copyProperties(accountDto,account);
        return account;
    }
    public static AccountDto accountToAccountDto(Accounts account,AccountDto accountDto){
        BeanUtils.copyProperties(account,accountDto);
        return accountDto;
    }
}
