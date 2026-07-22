package com.saikatsenportfolio.cards.mapper;

import com.saikatsenportfolio.cards.dto.CardDto;
import com.saikatsenportfolio.cards.entity.Cards;
import org.springframework.beans.BeanUtils;

public class CardDtoMapper {
    public static Cards cardDtoToCard(CardDto cardDto, Cards cards){
        BeanUtils.copyProperties(cardDto,cards);
        return cards;
    }
    public static CardDto cardToCardDto(Cards card,CardDto cardDto){
        BeanUtils.copyProperties(card,cardDto);
        return cardDto;
    }
}
