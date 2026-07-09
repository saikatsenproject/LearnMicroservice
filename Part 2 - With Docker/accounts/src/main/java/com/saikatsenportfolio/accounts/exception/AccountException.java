package com.saikatsenportfolio.accounts.exception;

public class AccountException extends RuntimeException{
    public AccountException(String msg,String customData){
        super(String.format(msg,customData));
    }
}
