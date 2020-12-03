package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.ProductDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDbRepo extends JpaRepository<ProductDb,Integer> {
    
    List<ProductDb> findByAge(int age);
    
    List<ProductDb> findByGender(String gender);
    
    List<ProductDb> findByAgeAndGender(int age, String gender);
}