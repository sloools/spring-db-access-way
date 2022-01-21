package me.song.jpqlexample.repository;

import me.song.jpqlexample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/* JpaRepository를 상속한 Repository를 JPQL을 사용하여 커스터마이징 */
@Repository
public interface QueryAnnotationRepository extends JpaRepository<Sample, Integer> {

    /* Spring Data JPA가 제공하는 쿼리 메소드에 @Query 어노테이션을 사용하여 직접 쿼리를 작성할 수 있음 */
    @Query("select s from sample s "
            + "where s.name =:name "
            + "ORDER BY s.id DESC "
    )
    List<Sample> findAllByName(@Param("name") String name); // 쿼리메소드 규칙 지키지 않아도 됨.
}
