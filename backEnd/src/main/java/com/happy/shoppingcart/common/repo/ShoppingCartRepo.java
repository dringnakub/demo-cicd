package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart,Integer> {
}
