package com.saikatsenportfolio.cards.service.impl;

import com.saikatsenportfolio.cards.dto.CardDto;
import com.saikatsenportfolio.cards.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICardService {
    ResponseEntity<ResponseDto> createCard(@RequestParam(value="email") String email);
    ResponseEntity<CardDto> findCard(@RequestParam(value="email") String email);
    ResponseEntity<Boolean> updateCard(CardDto cardDto);
    ResponseEntity<Boolean> deleteCard(@RequestParam(value="email") String email);
}
