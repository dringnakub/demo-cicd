package com.happy.shoppingcart.sample.controller;

import com.happy.shoppingcart.sample.service.SampleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SampleControllerTest {

    @InjectMocks
    private SampleController unitUnderTest;
    @Mock
    private SampleService sampleService;
    
    @Test
    void whenNoDataForInputAge20_resultShouldBeEmptyArray() {
        
        given(this.sampleService.getNameByAge(20)).willReturn(new ArrayList<>());
        
        String[] result = this.unitUnderTest.getSampleData(20);
        
        assertEquals(0, result.length);
    }
    
    @Test
    void whenDataExistsForInputAge20_resultShouldBeArrayWithReturnedData() {
        
        given(this.sampleService.getNameByAge(20)).willReturn(Arrays.asList("Toon", "Tum"));
        
        String[] result = this.unitUnderTest.getSampleData(20);
        
        assertArrayEquals(new String[] { "Toon", "Tum" }, result);
    }
}