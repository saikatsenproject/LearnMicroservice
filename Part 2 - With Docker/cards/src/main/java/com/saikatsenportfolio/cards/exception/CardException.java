package com.saikatsenportfolio.cards.exception;

public class CardException extends RuntimeException {
    public CardException(String message,String customData) {
        super(String.format(message,customData));
    }
}
