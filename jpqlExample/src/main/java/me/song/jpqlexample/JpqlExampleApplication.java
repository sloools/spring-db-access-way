package me.song.jpqlexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JPQL (객체지향 SQL)
 * :엔티티 객체를 대상으로 하는 쿼리
 *  sql로 변환됨
 *
 *  DB 테이블이 아닌 entity를 대상으로 쿼리를 하는 객체지향 쿼리이다.
 *  SQL을 추상화하여 특정 SQL에 의존하지 않는다. JPQL을 실행하면 데이터베이스 dialect(방언)를 참조하여 DB에 맞는 쿼리로 실행해준다
 */


@SpringBootApplication
public class JpqlExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpqlExampleApplication.class, args);
    }

}
