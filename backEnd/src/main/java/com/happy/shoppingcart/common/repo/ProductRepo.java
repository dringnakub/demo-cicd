package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    
}
