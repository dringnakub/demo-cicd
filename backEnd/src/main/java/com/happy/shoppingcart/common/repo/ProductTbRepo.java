package com.happy.shoppingcart.common.repo;

import java.util.List;

import com.happy.shoppingcart.common.entities.ProductTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTbRepo extends JpaRepository<ProductTb,Integer> {
    
    List<ProductTb> findByAge(int age);
    
    List<ProductTb> findByGender(String gender);
    
    List<ProductTb> findByAgeAndGender(int age, String gender);
}