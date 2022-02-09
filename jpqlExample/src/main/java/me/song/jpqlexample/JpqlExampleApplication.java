package me.song.jpqlexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * JPA의 객체지향 쿼리언어 종류
 * JPQL, QueryDSL, Criteria, NativeQuery 가 있다.
 * 이 중, 데이터베이스 의존적인 것은 NativeQuery 뿐이다.
 *
 * JPQL
 * :엔티티 객체를 대상으로 하는 쿼리
 *  sql로 변환됨
 *
 *  DB 테이블이 아닌 entity를 대상으로 쿼리를 하는 객체지향 쿼리이다.
 *  SQL을 추상화하여 특정 SQL에 의존하지 않는다. JPQL을 실행하면 데이터베이스 dialect(방언)를 참조하여 DB에 맞는 쿼리로 실행해준다
 *
 *
 *  QueryDsl
 *  자바코드로 작성하는 JPQL이라고 보면 됨
 * 쿼리를 스트링으로 작성할 필요가 없다
 * QueryDSL은 오류 나면 컴파일에러 나지만 JPQL은 런타임 에러가 난다 (치명적)
 *
 * ‘Querydsl JPA’ 는 프로젝트 내에서 @Entity 애노테이션을 선언한 클래스를 탐색하고 JPAAnnotationProcessor 를 이용하여 Q클래스를 생성한다.
 * 이렇게 생성된 Q클래스는 자바 언어가 가지는 정적코드의 장점을 활용하여 안전한 쿼리문을 작성할 수 있다
 *
 */


@SpringBootApplication
public class JpqlExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpqlExampleApplication.class, args);
    }

}
