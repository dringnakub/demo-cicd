package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.LoyaltyConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyConfigRepo extends JpaRepository<LoyaltyConfig,Integer> {
}
