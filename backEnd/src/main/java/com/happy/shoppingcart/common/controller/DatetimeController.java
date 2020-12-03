package com.happy.shoppingcart.common.controller;

import com.happy.shoppingcart.common.service.DatetimeService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class DatetimeController {
    
    @Autowired
    private DatetimeService datetimeService;
    
    @GetMapping("/api/v1/system/datetime")
    public LocalDateTime getSystemDatetime() {
        return this.datetimeService.getCurrentDatetime();
    }
    
    @PutMapping("/api/v1/system/datetime")
    public String updateSystemDatetime(@RequestBody DatetimeRequest request) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.datetimeService.setCurrentDatetime(LocalDateTime.from(df.parse(request.getNewDatetime())));
        return "updated";
    }
    
    private static final class DatetimeRequest {
        
        @Getter
        @Setter
        private String newDatetime; 
    }
}
