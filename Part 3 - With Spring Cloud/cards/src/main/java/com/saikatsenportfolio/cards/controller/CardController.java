package com.saikatsenportfolio.cards.controller;

import com.saikatsenportfolio.cards.dto.CardDto;
import com.saikatsenportfolio.cards.dto.ResponseDto;
import com.saikatsenportfolio.cards.service.CardService;
import com.saikatsenportfolio.cards.entity.Cards;
import com.saikatsenportfolio.cards.repository.CardRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

// this @Validated is optional which tells our spring boot application that we need to validated all @Valid in controller ,
// this is mandatory only when we use it with @RequestParam

@Tag(
        name = "Card Controller for CRUD API"
)
@RestController
@RequiredArgsConstructor
@Validated
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CardController {
    private final CardRepository cardRepository;
    private final CardService cardService;
    @GetMapping("/h2/data")
    ResponseEntity<List<Cards>> getAllCustData(){
        try{
            List<Cards> list=cardRepository.findAll();
            return ResponseEntity.ok(list);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }
    @Operation(
            summary = "Create Customer and Card API"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Card Details Created"
    )
    @PostMapping("/v1/card/create")
    ResponseEntity<ResponseDto> createCard(@RequestParam(value = "email") @Email(message = "Please provide valid Email Id") String email){
        return cardService.createCard(email);
    }
    @GetMapping("/v1/card/find")
    ResponseEntity<CardDto> findCard(@RequestParam(value = "email") @Email(message = "Please provide valid Email Id") String email){
        return cardService.findCard(email);
    }
    @PutMapping("/v1/card/update")
    ResponseEntity<Boolean> updateCard(@Valid @RequestBody CardDto cardDto){
        return cardService.updateCard(cardDto);
    }
    @DeleteMapping("/v1/card/delete")
    ResponseEntity<Boolean> deleteCard(@RequestParam(value = "email") @Email(message = "Please provide valid Email Id") String email){
        return cardService.deleteCard(email);
    }
}
