package com.saikatsenportfolio.cards.constant;

public class CardConstant {
    private CardConstant(){
    }
    public static final String SUCCESS_STATUS="200";
    public static final String NOT_FOUND_STATUS="404";
    public static final String FORBIDDEN_STATUS="403";
    public static final String ERROR_STATUS="400";
    public static final String SUCCESS_MSG="CARD DETAILS CREATED SUCCESSFULLY";
    public static final String ERROR_CARD_EMAIL_MSG="CARD ALREADY EXISTS WITH EMAIL ID %s";
    public static final String CARD_TYPE_CREDIT="CREDIT";
    public static final Long CARD_TOTAL_LIMIT=100000L;
    public static final Long CARD_AVAILABLE_AMOUNT=100000L;
    public static final Long CARD_AMOUNT_USED=0L;
    public static final String ERROR_NO_CARD_EMAIL_MSG="NO CARD EXISTS WITH EMAIL ID %s";
    public static final String ERROR_NO_CARD_FOUND_WITH_CARD_NO="NO CARD EXISTS WITH CARD NUMBER %s";
}
