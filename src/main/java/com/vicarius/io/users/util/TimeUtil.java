package com.vicarius.io.users.util;

import java.time.LocalTime;


public class TimeUtil {


    public static boolean isDayTime() {
        LocalTime now = LocalTime.now();
        return now.isAfter(LocalTime.of(9, 0)) && now.isBefore(LocalTime.of(17, 0));
    }



}
