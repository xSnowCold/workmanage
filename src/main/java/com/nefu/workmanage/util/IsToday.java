package com.nefu.workmanage.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class IsToday {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    //获取今天的日期
    LocalDateTime localDateTime = LocalDateTime.now();
    String nowDay = sf.format(localDateTime);

    //对比的时间
    public boolean isNow(LocalDateTime dateTime){
        String day = sf.format(dateTime);
        return day.equals(nowDay);
    }
}
