package com.vicarius.io.users.data_base;
import lombok.Data;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;




@Configuration
@Data
public class PoolPropertiesConfig {

    private DataBaseProps dataBaseProps;


    @Value("${connectionPool.testOnConnect}")
    private Boolean testOnConnect;

    @Value("${connectionPool.testOnReturn}")
    private Boolean testOnReturn;

    @Value("${connectionPool.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${connectionPool.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value("${connectionPool.validationQuery}")
    private String validationQuery;

    @Value("${connectionPool.validationQueryTimeout}")
    private Integer validationQueryTimeout;

    @Value("${connectionPool.timeBetweenEvictionRuns}")
    private Integer timeBetweenEvictionRuns;

    @Value("${connectionPool.removeAbandoned}")
    private Boolean removeAbandoned;

    @Value("${connectionPool.removeAbandonedTimeout}")
    private Integer removeAbandonedTimeout;


    @Autowired
    public PoolPropertiesConfig(DataBaseProps dataBaseProps) {
        this.dataBaseProps = dataBaseProps;
    }


    @Bean
    public PoolProperties getPoolProperties() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl("jdbc:mysql://" + dataBaseProps.getHost() + ":" + dataBaseProps.getPort() + "/" + dataBaseProps.getSchemaName());
        Properties dbProperties = new Properties();
        dbProperties.setProperty("useSSL","true");
        dbProperties.setProperty("requireSSL","true");
        poolProperties.setDbProperties( dbProperties);
        poolProperties.setUsername(dataBaseProps.getUsername());
        poolProperties.setDriverClassName(dataBaseProps.getClassName());
        poolProperties.setTestOnConnect(getTestOnConnect());
        poolProperties.setTestOnReturn(getTestOnReturn());
        poolProperties.setTestOnBorrow(getTestOnBorrow());
        poolProperties.setTestWhileIdle(getTestWhileIdle());
        poolProperties.setValidationQuery(getValidationQuery());
        poolProperties.setValidationQueryTimeout(getValidationQueryTimeout());
        poolProperties.setTimeBetweenEvictionRunsMillis(getTimeBetweenEvictionRuns());
        poolProperties.setRemoveAbandonedTimeout(getRemoveAbandonedTimeout());
        poolProperties.setRemoveAbandoned(getRemoveAbandoned());
        poolProperties.setPassword(dataBaseProps.getPassword());
        return poolProperties;
    }

}


