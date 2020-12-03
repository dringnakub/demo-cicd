package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.common.entities.ProductDb;
import com.happy.shoppingcart.common.repo.ProductDbRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    
    @InjectMocks
    private ProductService unitUnderTest;
    @Mock
    private ProductDbRepo productDbRepos;

    @Test
    void getProductListWhenInputAgeAndGenderIsNull_resultIsReturnedByFindAll() {
        
        List<ProductDb> productDbList = new ArrayList<>();
        ProductDb productDb = new ProductDb();
        productDb.setProductId(15);
        productDb.setProductName("OMG-Gossip Girl");
        productDbList.add(productDb);
        given(productDbRepos.findAll()).willReturn(productDbList);
        
        List<ProductDb> resultList = this.unitUnderTest.getProductList(null, null);
        
        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }
    
    @Test
    void getProductListWhenInputOnlyAge_resultIsReturnedByFindByAge() {

        List<ProductDb> productDbList = new ArrayList<>();
        ProductDb productDb = new ProductDb();
        productDb.setProductId(15);
        productDb.setProductName("OMG-Gossip Girl");
        productDbList.add(productDb);
        given(productDbRepos.findByAge(15)).willReturn(productDbList);

        List<ProductDb> resultList = this.unitUnderTest.getProductList(15, null);

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }

    @Test
    void getProductListWhenInputOnlyGender_resultIsReturnedByFindByGender() {

        List<ProductDb> productDbList = new ArrayList<>();
        ProductDb productDb = new ProductDb();
        productDb.setProductId(15);
        productDb.setProductName("OMG-Gossip Girl");
        productDbList.add(productDb);
        given(productDbRepos.findByGender("male")).willReturn(productDbList);

        List<ProductDb> resultList = this.unitUnderTest.getProductList(null, "male");

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }

    @Test
    void getProductListWhenInputAgeAndGender_resultIsReturnedByFindByAgeAndGender() {

        List<ProductDb> productDbList = new ArrayList<>();
        ProductDb productDb = new ProductDb();
        productDb.setProductId(15);
        productDb.setProductName("OMG-Gossip Girl");
        productDbList.add(productDb);
        given(productDbRepos.findByAgeAndGender(15, "male")).willReturn(productDbList);

        List<ProductDb> resultList = this.unitUnderTest.getProductList(15, "male");

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }
}