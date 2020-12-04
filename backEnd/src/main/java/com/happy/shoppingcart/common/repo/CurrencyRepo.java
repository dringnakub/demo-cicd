package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.Currency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<Currency,String> {

    public Currency findByCurrencyCode(String currencyCode);
    
}