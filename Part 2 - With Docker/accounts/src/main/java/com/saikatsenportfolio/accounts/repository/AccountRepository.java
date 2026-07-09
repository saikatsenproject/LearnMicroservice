package com.saikatsenportfolio.accounts.repository;

import com.saikatsenportfolio.accounts.entity.Accounts;
import com.saikatsenportfolio.accounts.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Long> {
    Optional<Accounts> findByCustomerId(Long customerId);

    // we only need @Transactional and @Modifying if we are use our own delete logic
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
