package me.song.jpqlexample.repository;

import me.song.jpqlexample.entity.Sample;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@Repository
public class JpqlSampleRepository {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample-unit");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    // select
    public List<Sample> getSamples(){
        String query = "SELECT s FROM sample s"; // JPQL : entity 객체를 대상으로 쿼리함

        List<Sample> result = em.createQuery(query).getResultList();

        return result;
    }

    // where 조건 사용 (프로퍼티 바인딩)
    public List<Sample> getSampleWhereNameSong(){
        String query = "SELECT s FROM sample s where s.name =:username";

        List<Sample> result = em.createQuery(query).setParameter("username", "Song").getResultList();

        return result;
    }

    // ORDER BY & 페이징
    public List<Sample> getSamplePaging(){
        String query = "SELECT s FROM sample s order by s.id desc";

        List<Sample> result = em.createQuery(query)
                                .setFirstResult(5) // 조회 시작 위치
                                .setMaxResults(3)  // 조회할 데이터 수
                                .getResultList();

        return result;
    }
}
