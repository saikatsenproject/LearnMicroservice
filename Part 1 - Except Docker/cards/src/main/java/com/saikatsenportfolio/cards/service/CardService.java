package com.saikatsenportfolio.cards.service;

import com.saikatsenportfolio.cards.constant.CardConstant;
import com.saikatsenportfolio.cards.dto.CardDto;
import com.saikatsenportfolio.cards.dto.ResponseDto;
import com.saikatsenportfolio.cards.entity.Cards;
import com.saikatsenportfolio.cards.exception.CardException;
import com.saikatsenportfolio.cards.mapper.CardDtoMapper;
import com.saikatsenportfolio.cards.repository.CardRepository;
import com.saikatsenportfolio.cards.service.impl.ICardService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CardService implements ICardService {
    private CardRepository cardRepository;
    @Override
    public ResponseEntity<ResponseDto> createCard(@RequestParam(value="email") String email) {

        Optional<Cards> listCards=cardRepository.findByEmailId(email);
        if(listCards.isPresent()){
            throw new CardException(CardConstant.ERROR_CARD_EMAIL_MSG,email);
        }
        Cards card= new Cards();
        String uniqueCardNumber=String.valueOf((System.currentTimeMillis()%100000000)+(long)(Math.random()*100));
        card.setCardNumber(uniqueCardNumber);
        card.setEmailId(email);
        card.setCardType(CardConstant.CARD_TYPE_CREDIT);
        card.setTotalLimit(CardConstant.CARD_TOTAL_LIMIT);
        card.setAmountUsed(CardConstant.CARD_AMOUNT_USED);
        card.setAvailableAmount(CardConstant.CARD_AVAILABLE_AMOUNT);
        cardRepository.saveAndFlush(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardConstant.SUCCESS_STATUS,CardConstant.SUCCESS_MSG));
    }

    @Override
    public ResponseEntity<CardDto> findCard(String email) {
        Optional<Cards> listCards=cardRepository.findByEmailId(email);
        if(!listCards.isPresent()){
            throw new CardException(CardConstant.ERROR_NO_CARD_EMAIL_MSG,email);
        }
        CardDto cardDto= CardDtoMapper.cardToCardDto(listCards.get(),new CardDto());
        return ResponseEntity.status(HttpStatus.CREATED).body(cardDto);
    }

    @Override
    public ResponseEntity<Boolean> updateCard(CardDto cardDto) {
        String cardNo=cardDto.getCardNumber();
        Optional<Cards> listCards=cardRepository.findByCardNumber(cardNo);
        if(!listCards.isPresent()){
            throw new CardException(CardConstant.ERROR_NO_CARD_FOUND_WITH_CARD_NO,cardNo);
        }
        Cards card= CardDtoMapper.cardDtoToCard(cardDto,listCards.get());
        cardRepository.saveAndFlush(card);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> deleteCard(String email) {
        Optional<Cards> listCards=cardRepository.findByEmailId(email);
        if(!listCards.isPresent()){
            throw new CardException(CardConstant.ERROR_NO_CARD_EMAIL_MSG,email);
        }
        Cards card= listCards.get();
        Long cardId=card.getCardId();
        cardRepository.deleteById(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
