package com.vicarius.io.users.data_base;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneOffset;

@Component
public class DataSourceScheduler {

    @Scheduled(cron = "0 0 * * * *") // This cron expression runs the method every hour
    public void handleDataSource() {
        // Logic to switch between MySQL and ElasticSearch based on the time of day
        // For example, switch at 09:00 and 17:01 UTC
        LocalTime now = LocalTime.now(ZoneOffset.UTC);
        if (now.isAfter(LocalTime.of(9, 0)) && now.isBefore(LocalTime.of(17, 0))) {
            // Use MySQL
        } else {
            // Use ElasticSearch
        }
    }
}
