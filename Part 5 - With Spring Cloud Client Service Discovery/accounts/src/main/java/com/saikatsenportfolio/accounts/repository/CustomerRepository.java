package com.saikatsenportfolio.accounts.repository;

import com.saikatsenportfolio.accounts.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {

    Optional<Customers> findByEmail(String email);
    Optional<Customers> findByMobileNumber(String mobileNumber);
}
