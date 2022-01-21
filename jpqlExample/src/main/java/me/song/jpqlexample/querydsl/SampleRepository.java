package me.song.jpqlexample.querydsl;

import com.querydsl.core.Tuple;
import me.song.jpqlexample.entity.CustomerInfo;
import me.song.jpqlexample.entity.Sample;

import java.util.List;

/**
 * QueryDsl을 사용하여 구현한 쿼리를 모아놓은 interface
 */
public interface SampleRepository {
    List<Sample> findAllByNameOrderById(String name);

    List<CustomerInfo> findCustomerInfo();
}
