package me.song.mybatis.service;

import me.song.mybatis.entity.Sample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SampleServiceTest {

    @Autowired
    SampleService sampleService;

    @Test
    public void findOneSampleTest(){
        String me = "Song";

        Sample meSample = sampleService.findSampleByName(me);
        System.out.println(meSample.toString());

    }

    @Test
    public void findAllSampleTest(){
        String me = "Song";

        List<Sample> meSamples = sampleService.findAllSampleByName(me);
        meSamples.forEach(x -> System.out.println(x.toString()));

    }
}
