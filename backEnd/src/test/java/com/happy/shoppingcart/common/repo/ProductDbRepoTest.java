package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.ProductTb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductTbRepoTest {

    @Autowired
    private ProductTbRepo unitUnderTest;
    
    @Test
    void testFindByAge_shouldSuccess() {
        
        List<ProductTb> result = unitUnderTest.findByAge(15);
        assertNotNull(result);
    }
    
    @Test
    void testFindByGender_shouldSuccess() {

        List<ProductTb> result = unitUnderTest.findByGender("male");
        assertNotNull(result);
    }

    @Test
    void testFindByAndAndGender_shouldSuccess() {

        List<ProductTb> result = unitUnderTest.findByAgeAndGender(15, "male");
        assertNotNull(result);
    }
}