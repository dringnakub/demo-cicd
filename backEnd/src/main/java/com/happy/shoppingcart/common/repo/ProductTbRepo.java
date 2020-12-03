package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.ProductTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTbRepo extends JpaRepository<ProductTb,Integer> {
    
}