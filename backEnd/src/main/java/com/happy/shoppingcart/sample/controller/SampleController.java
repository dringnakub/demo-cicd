package com.happy.shoppingcart.sample.controller;

import com.happy.shoppingcart.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Profile("sample")
@RestController
public class SampleController {
    
    @Autowired
    private SampleService sampleService;
    
    @GetMapping("/api/sample/data")
    public String[] getSampleData(@RequestParam("age") int age) {
        return sampleService.getNameByAge(age).toArray(new String[0]);
    }
}
