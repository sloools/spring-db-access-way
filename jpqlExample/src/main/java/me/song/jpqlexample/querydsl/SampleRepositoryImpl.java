package me.song.jpqlexample.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.song.jpqlexample.entity.CustomerInfo;
import me.song.jpqlexample.entity.Sample;
import org.springframework.stereotype.Repository;

import java.util.List;
import static me.song.jpqlexample.entity.QSample.sample;
import static me.song.jpqlexample.entity.QCustomer.customer;

/**
 * QueryDsl Interface를 구현하여 쿼리를 작성한 Impl 클래스
 */
@Repository
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepository{

    /* Bean으로 등록항 JPAQueryFactory를 주입받아 사용 */
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Sample> findAllByNameOrderById(String name) {
        List<Sample> result = jpaQueryFactory.selectFrom(sample)
                .where(sample.name.eq(name))
                .orderBy(sample.id.asc())
                .fetch();
        return result;

    }

    @Override
    public List<CustomerInfo> findCustomerInfo() {
        // .select()는 Tuple을 리턴한다. Projections를 사용하여 DTO 클래스로 Convert할 수 있다

        List<CustomerInfo> result = jpaQueryFactory
                .select(Projections.bean(CustomerInfo.class,
                        sample.name,sample.age, customer.job
                 ))
                .from(sample)
                .leftJoin(customer)
                .on(sample.name.eq(customer.name))
                .fetch();

        return result;
    }
}
