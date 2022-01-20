package me.song.jpqlexample.repository;

import me.song.jpqlexample.entity.Sample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryAnnotationTest {

    @Autowired
    QueryAnnotationRepository queryAnnotationRepository;

    @Test
    @DisplayName("Query Annotation Select Test")
    public void getSamplesTest(){
            List<Sample> sample = queryAnnotationRepository.findAllByName("song");

            for(Sample s : sample){
                System.out.println(s.toString());
        }
    }
}
