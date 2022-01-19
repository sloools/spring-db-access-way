package me.song.jpqlexample.repository;

import me.song.jpqlexample.entity.Sample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class jpqlSampleRepositoryTest {

    @Autowired
    JpqlSampleRepository jpqlSampleRepository;

    @Test
    @DisplayName("Select Test")
    public void getSamplesTest(){
        for(Sample sample :jpqlSampleRepository.getSamples()){
            System.out.println(sample.toString());
        }
    }

    @Test
    @DisplayName("where 조건 사용 (프로퍼티 바인딩) Test")
    public void getSampleWhereNameSongTest(){
        for(Sample sample :jpqlSampleRepository.getSampleWhereNameSong()){
            System.out.println(sample.toString());
        }
    }

    @Test
    @DisplayName("ORDER BY & 페이징 Test")
    public void getSamplePagingTest(){
        for(Sample sample :jpqlSampleRepository.getSamplePaging()){
            System.out.println(sample.toString());
        }
    }
}
