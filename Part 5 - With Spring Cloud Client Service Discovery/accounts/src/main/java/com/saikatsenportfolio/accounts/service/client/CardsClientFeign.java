package com.saikatsenportfolio.accounts.service.client;

import com.saikatsenportfolio.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards")
public interface CardsClientFeign {
    @GetMapping("/v1/card/find")
    ResponseEntity<CardDto> findCard(@RequestParam String email);
}
