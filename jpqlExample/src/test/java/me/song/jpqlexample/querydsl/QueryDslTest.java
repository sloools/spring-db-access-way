package me.song.jpqlexample.querydsl;

import me.song.jpqlexample.entity.Sample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryDslTest {

    @Autowired
    QueryDslRepository queryDslRepository;

    @Test
    @DisplayName("QueryDsl Left Join Test")
    public void querydslLeftJoinTest(){
        for(Sample sample :queryDslRepository.findSampleDsl()){
            System.out.println(sample.toString());
        }
    }
}
