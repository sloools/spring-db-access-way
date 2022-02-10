package me.song.jpqlexample.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.song.jpqlexample.entity.Customer;
import me.song.jpqlexample.entity.CustomerInfo;
import me.song.jpqlexample.entity.CustomerWithNames;
import me.song.jpqlexample.entity.Sample;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import static me.song.jpqlexample.entity.QSample.sample;
import static me.song.jpqlexample.entity.QCustomer.customer;

/**
 * QueryDsl Interface를 구현하여 쿼리를 작성한 Impl 클래스
 */
@Repository
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepository{

    /* Bean으로 등록할 JPAQueryFactory를 주입받아 사용 */
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

    /* Select 절에서 SubQuery 사용하는 방법
    * 객체지향적으로 ORM이 구성돼어있다면 서브쿼리가 필요한 일은 거의 없어야 한다.
    * 서브쿼리는 성능면에서 좋지 않다.
    * 서브쿼리를 실행하는 비용이 추가적으로 발생하기 때문이다.
    * 서브쿼리는 비영속적인 임시 테이블이기 때문에 이를 저장하기 위해 메모리에 적재되어야 하기 때문에 메모리상 성능에도 영향을 미친다.
    *
    * 서브쿼리를 사용하는 것보다는 join을 사용하든지 쿼리를 나눠서 실행하든지 다른 방법을 사용해야 하는 편이 낫다.
    * 서브쿼리는 쿼리에서 안티패턴이다.
    **/
    @Override
    public List<CustomerWithNames> findNamesAndJobByJob(String job) {
        List<CustomerWithNames> customerWithNamesList =
                jpaQueryFactory.select(
                            Projections.bean(CustomerWithNames.class,
                            customer.id,
                            customer.name.as("name1"),
                            sample.age,
                            sample.name.as("name2"), // 다른 테이블에서 가져온 중복되는 이름은 alias 설정으로 DTO와 맞춘다
                                    ExpressionUtils.as(
                                            JPAExpressions.select(customer.job)
                                                    .from(customer)
                                                    .where(customer.name.eq("Song")),
                                            "job"
                                    ) // SELECT절 내부 서브 쿼리 사용하는 방법(ExpressionUtils). QueryDsl에서는 from() 내부에서 subquery사용은 불가능하다
                            )
                        )
                        .from(sample)
                        .innerJoin(customer)
                        .on(sample.id.eq(customer.id))
                        .where(sample.age.gt(10),
                        customer.id.lt(10)) // where절에서 and 표현은 콤마(,)로 표현
                        .orderBy(sample.id.asc())
                        .fetch();

        return customerWithNamesList;
    }

    // QueryDSL을 사용한 Dynamic Query
    @Override
    public List<Customer> findNamesDynamicQuery(String name, String job) {

        List<Customer> customerDynamicQuery =
                jpaQueryFactory.selectFrom(customer)
                                    .where(eqName(name), // QueryDSL의 where() 내부에서 파라미터가 null이 올 경우 조건문에서 제외한다
                                           eqJob(job)
                                            )
                                    .groupBy(Expressions.stringPath("name")) // Group By Alias
                                    .fetch();

        return customerDynamicQuery;
    }

    private BooleanExpression eqName(String name){
        if(StringUtils.isEmpty(name)){
            return null;
        }
        return customer.name.eq(name);
    }

    private BooleanExpression eqJob(String job){
        if(StringUtils.isEmpty(job)){
            return null;
        }
        return customer.job.eq(job);
    }

}
