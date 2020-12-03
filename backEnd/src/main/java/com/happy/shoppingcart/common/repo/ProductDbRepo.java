package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.ProductDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDbRepo extends JpaRepository<ProductDb,Integer> {
    
}