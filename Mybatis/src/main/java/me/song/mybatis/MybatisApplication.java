package me.song.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MyBatis
 *
 * 실무에서 굉장히 많이 쓰이는 SQL Mapper
 * 자바 객체와 SQL 사이에 자동 맵핑을 도와주는 프레임워크이다.
 * XML형태로 쿼리를 관리해주며 JDBC의 거의 모든 기능을 제공한다.
 *
 */
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
