package com.vicarius.io.users.data_base;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class DataSourceConfig {


    @Bean
    public DataSource dataSource(PoolProperties poolProperties){
        DataSource dataSource = new DataSource(poolProperties);
        return dataSource;
    }



}
