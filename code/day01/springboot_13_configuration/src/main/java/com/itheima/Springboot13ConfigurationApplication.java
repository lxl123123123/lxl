package com.itheima;

import com.alibaba.druid.pool.DruidDataSource;
import com.itheima.config.ServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Springboot13ConfigurationApplication {


    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DruidDataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Springboot13ConfigurationApplication.class, args);
        ServerConfig bean = run.getBean(ServerConfig.class);
        System.out.println(bean);
        DruidDataSource ds = run.getBean(DruidDataSource.class);
        System.out.println(ds.getDriverClassName());
    }

}
