package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.common.entities.ProductTb;
import com.happy.shoppingcart.common.repo.ProductTbRepo;
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
    private ProductTbRepo ProductTbRepos;

    @Test
    void getProductListWhenInputAgeAndGenderIsNull_resultIsReturnedByFindAll() {
        
        List<ProductTb> ProductTbList = new ArrayList<>();
        ProductTb ProductTb = new ProductTb();
        ProductTb.setProductId(15);
        ProductTb.setProductName("OMG-Gossip Girl");
        ProductTbList.add(ProductTb);
        given(ProductTbRepos.findAll()).willReturn(ProductTbList);
        
        List<ProductTb> resultList = this.unitUnderTest.getProductList(null, null);
        
        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }
    
    @Test
    void getProductListWhenInputOnlyAge_resultIsReturnedByFindByAge() {

        List<ProductTb> ProductTbList = new ArrayList<>();
        ProductTb ProductTb = new ProductTb();
        ProductTb.setProductId(15);
        ProductTb.setProductName("OMG-Gossip Girl");
        ProductTbList.add(ProductTb);
        given(ProductTbRepos.findByAge(15)).willReturn(ProductTbList);

        List<ProductTb> resultList = this.unitUnderTest.getProductList(15, null);

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }

    @Test
    void getProductListWhenInputOnlyGender_resultIsReturnedByFindByGender() {

        List<ProductTb> ProductTbList = new ArrayList<>();
        ProductTb ProductTb = new ProductTb();
        ProductTb.setProductId(15);
        ProductTb.setProductName("OMG-Gossip Girl");
        ProductTbList.add(ProductTb);
        given(ProductTbRepos.findByGender("male")).willReturn(ProductTbList);

        List<ProductTb> resultList = this.unitUnderTest.getProductList(null, "male");

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }

    @Test
    void getProductListWhenInputAgeAndGender_resultIsReturnedByFindByAgeAndGender() {

        List<ProductTb> ProductTbList = new ArrayList<>();
        ProductTb ProductTb = new ProductTb();
        ProductTb.setProductId(15);
        ProductTb.setProductName("OMG-Gossip Girl");
        ProductTbList.add(ProductTb);
        given(ProductTbRepos.findByAgeAndGender(15, "male")).willReturn(ProductTbList);

        List<ProductTb> resultList = this.unitUnderTest.getProductList(15, "male");

        assertEquals(1, resultList.size());
        assertEquals(15, resultList.get(0).getProductId());
        assertEquals("OMG-Gossip Girl", resultList.get(0).getProductName());
    }
}