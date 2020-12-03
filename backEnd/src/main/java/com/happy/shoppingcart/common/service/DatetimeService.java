package com.happy.shoppingcart.common.service;

import java.time.LocalDateTime;

public interface DatetimeService {
    
    LocalDateTime getCurrentDatetime();
    
    void setCurrentDatetime(LocalDateTime datetime);
}
