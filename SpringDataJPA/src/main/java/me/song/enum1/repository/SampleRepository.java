package me.song.enum1.repository;

import me.song.enum1.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface SampleRepository extends JpaRepository<Person, Integer> {
    @QueryHints(value = {@QueryHint(name = "org.hibernate.comment", value = "sampleRepository.findAllByName")})
    Optional<List<Person>> findAllByName(String name);
}
