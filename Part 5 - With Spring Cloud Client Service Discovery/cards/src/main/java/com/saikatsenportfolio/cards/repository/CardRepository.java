package com.saikatsenportfolio.cards.repository;

import com.saikatsenportfolio.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Cards,Long> {
    Optional<Cards> findByEmailId(String emailId);
    Optional<Cards> findByCardNumber(String cardNumber);
}
