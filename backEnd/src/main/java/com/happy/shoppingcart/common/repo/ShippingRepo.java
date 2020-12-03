package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepo extends JpaRepository<Shipping,Integer> {
}
