package com.happy.shoppingcart.common.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

@Profile("test")
@Service
public class DatetimeServiceTestImpl implements DatetimeService {
    
    private AtomicReference<LocalDateTime> systemDatetime = new AtomicReference<>();
    
    @PostConstruct
    public void setup() {
        this.systemDatetime.set(LocalDateTime.now());
    }
    
    @Override
    public LocalDateTime getCurrentDatetime() {
        return this.systemDatetime.get();
    }

    @Override
    public void setCurrentDatetime(LocalDateTime datetime) {
        if (datetime == null) throw new IllegalArgumentException("Datetime cannot be null.");
        this.systemDatetime.set(datetime);
    }
}
