package com.happy.shoppingcart.common.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Profile("!test")
@Service
public class DatetimeServiceImpl implements DatetimeService {
    
    @Override
    public LocalDateTime getCurrentDatetime() {
        return LocalDateTime.now();
    }

    @Override
    public void setCurrentDatetime(LocalDateTime datetime) {
        // NOOP.
    }
}
