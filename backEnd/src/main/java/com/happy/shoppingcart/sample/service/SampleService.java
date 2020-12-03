package com.happy.shoppingcart.sample.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Profile("sample")
@Service
public class SampleService {
    
    private Map<Integer, List<String>> peopleNameByAge = null;
    
    @PostConstruct
    public void setup() {
        this.peopleNameByAge = new HashMap<>();
        this.peopleNameByAge.put(15, Arrays.asList("Apple", "Cherry"));
        this.peopleNameByAge.put(17, Arrays.asList("Man", "Mike"));
        this.peopleNameByAge.put(23, Arrays.asList("Toon", "Kook", "Hoo"));
    }
    
    public List<String> getNameByAge(int age) {

        if (this.peopleNameByAge.containsKey(age)) return this.peopleNameByAge.get(age);
        return new ArrayList<>();
    }
}
