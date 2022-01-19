package me.song.jpqlexample.repository;

import me.song.jpqlexample.entity.Sample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpqlNamedQueryTest {
    @Autowired
    JpqlNamedQueryRepository jpqlNamedQueryRepository;

    @Test
    @DisplayName("Named Query Select Test")
    public void getSamplesTest(){
        for(Sample sample :jpqlNamedQueryRepository.getSamplesByAge()){
            System.out.println(sample.toString());
        }
    }
}
