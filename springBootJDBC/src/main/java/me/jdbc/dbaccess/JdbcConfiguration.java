package me.jdbc.dbaccess;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {

    // setter를 사용하여 Bean을 등록하는 방법
    @Bean
    DataSource dataSource(){

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setUrl("jdbc:mysql://localhost:3306/study?useUnicode=true@characterEncoding=utf8&serverTimezone=Asia/Seoul");

        return dataSource;
    }

    // Builder를 사용하여 Bean을 등록하는 방법
//    @Bean
//    public DataSource datasource() {
//        return DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url("jdbc:mysql://localhost:3306/sample")
//                .username("root")
//                .password("root")
//                .build();
//    }
}

/**
Spring Boot AutoConfigurer  
1. DataSource가 빈으로 등록되어 있는지 확인
2. 먄약에 등록되어있으면, 등록된 빈을 사용
3. 만약에 등록되지 않았으면, 자동으로 DataSource를 만들려고 시도함. -> application.propeties 뒤져서 {spring.datasource.username} 구성요소 4가지가 다 있으면
   빈으로 자동으로 등록해줌
**/