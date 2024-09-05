package com.vicarius.io.users.data_base;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataSourceInitializer {

    @PostConstruct
    public void init() {
        handleDataSource();
    }

    public void handleDataSource() {
        // Logic to switch between MySQL and ElasticSearch based on the time of day
    }
}
