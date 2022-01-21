package me.song.jpqlexample.querydsl;

import me.song.jpqlexample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JpaRepository<>와 QueryDsl 메소드를 정의한 인터페이스를 둘 다 상속
 * Service 계층에서는 이 인터페이스를 주입받아 시용하면 된다.
 */
public interface SampleJpaRepository extends JpaRepository<Sample, Integer>, SampleRepository {

    List<Sample> findAllByNameOrderById(String name);

    List<Sample> findAllByNameOrderByIdDesc(String name);
}
