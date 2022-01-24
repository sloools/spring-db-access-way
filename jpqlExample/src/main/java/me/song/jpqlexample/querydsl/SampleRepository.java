package me.song.jpqlexample.querydsl;

import com.querydsl.core.Tuple;
import me.song.jpqlexample.entity.CustomerInfo;
import me.song.jpqlexample.entity.CustomerWithNames;
import me.song.jpqlexample.entity.Sample;

import java.util.List;

/**
 * QueryDsl을 사용하여 구현한 쿼리를 모아놓은 interface
 */
public interface SampleRepository {
    List<Sample> findAllByNameOrderById(String name);

    List<CustomerInfo> findCustomerInfo();

    List<CustomerWithNames> findNamesAndJobByJob(String job);
}


/***
 * eq("something"): = 'something'
 * ne("something"): != 'something'
 * eq("something").not(): != 'something'
 * like("%something"): like '%something'
 * startsWith("something"): like 'something%'
 * contains("something"): like '%something%'
 * isNull(): is null
 * isNotNull(): is not null
 * isEmpty(): 길이가 0
 * isNotEmpty(): 길이가 0이 아님
 * in("foo", "bar"): in("foo", "bar")
 * notIn("foo", "bar"): not in("foo", "bar")
 * in("foo", "bar").not(): not in("foo", "bar")
 * between(20, 30): between 20, 30
 * notBetween(20, 30): not between 20, 30
 * between(20, 30).not(): not between 20, 30
 * gt(28): > 28
 * goe(28): >= 28 (Greater Or Equel ?)
 * lt(28): < 28
 * loe(28): <= 28
 * 결과 매핑
 *
 *
 * fetch(): 리스트 반환, 결과가 없는 경우 빈 리스트 반환
 * fetchOne(): 한 건 조회
 * 결과가 없는 경우: null 반환
 * 결과가 여러 개인 경우: NonUniqueResultException 발생
 * fetchFirst(): 처음 한 건 조회
 * limit(1).fetch()와 동일
 * fetchResults(): 결과에 페이지 정보 포함, total count 쿼리 추가 수행
 * total count 쿼리는 count(id) 사용
 * fetchCount(): count 쿼리 수행
 */