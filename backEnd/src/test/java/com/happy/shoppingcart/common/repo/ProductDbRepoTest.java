package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.ProductDb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductDbRepoTest {

    @Autowired
    private ProductDbRepo unitUnderTest;
    
    @Test
    void testFindByAge_shouldSuccess() {
        
        List<ProductDb> result = unitUnderTest.findByAge(15);
        assertNotNull(result);
    }
    
    @Test
    void testFindByGender_shouldSuccess() {

        List<ProductDb> result = unitUnderTest.findByGender("male");
        assertNotNull(result);
    }

    @Test
    void testFindByAndAndGender_shouldSuccess() {

        List<ProductDb> result = unitUnderTest.findByAgeAndGender(15, "male");
        assertNotNull(result);
    }
}