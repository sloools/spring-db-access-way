package me.song.jpqlexample.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import me.song.jpqlexample.entity.CustomerInfo;
import me.song.jpqlexample.entity.QCustomer;
import me.song.jpqlexample.entity.QSample;
import me.song.jpqlexample.entity.Sample;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * QueryDSL의 기본적인 사용 방법
 */
@Repository
public class QueryDslRepository {

    @PersistenceContext
    private EntityManager entityManager;

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample-unit");
//    EntityManager em = emf.createEntityManager();

    public List<Sample> findSampleDsl(){
        JPAQuery<Sample> query = new JPAQuery<>(entityManager);
        QSample qSample = QSample.sample; // QueryDsl에서는 기본 인스턴스를 보관하고 있기 때문에 이처럼 사용 가능
        QCustomer qCustomer = QCustomer.customer;

        List<Sample> result = query.select(qSample)
                .from(qSample)
                .leftJoin(qCustomer)
                .on(qSample.name.eq(qCustomer.name))
                .fetch();
        return result;

    }
}
