package me.song.jpqlexample.repository;

import me.song.jpqlexample.entity.Sample;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Named Query : 정적 쿼리
 * 런타임 에러 방지하기 위해 쿼리를 정적으로 미리 선언 (정적쿼리 사용시 오류 있으면 컴파일오류 남)
 * 파싱된 결과를 재사용할 수 있음 (사용된 쿼리는 캐싱해놓음)
 */

@Repository
public class JpqlNamedQueryRepository {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample-unit");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    public List<Sample> getSamplesByAge(){
        List<Sample> result = em.createNamedQuery("Sample.findByage", Sample.class)
                .setParameter("age", 29)
                .getResultList();

        return result;
    }

}
