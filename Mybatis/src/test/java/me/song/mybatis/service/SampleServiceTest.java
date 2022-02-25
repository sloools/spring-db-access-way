package me.song.mybatis.service;

import me.song.mybatis.entity.Sample;
import me.song.mybatis.entity.SampleReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

    @Test
    public void findAllSampleInNameTest(){
        List<String> names = new ArrayList<>();
        names.add("song");

        List<Sample> samples = sampleService.findAllSampleInName(names);
        samples.forEach(x -> System.out.println(x.toString()));
    }

    @Test
    public void findAllSampleInNoNameTest(){
        List<String> names = new ArrayList<>();
        names.add("wdawdawd");

        List<Sample> samples = sampleService.findAllSampleInName(names);
        samples.forEach(x -> System.out.println(x.toString()));
    }

    @Test
    public void findAllSampleInNameBySampleListTest(){
        List<SampleReq> samples = new ArrayList<>();
        samples.add(new SampleReq("Song"));
        samples.add(new SampleReq("Andrew"));

        List<Sample> sampleList = sampleService.findAllSampleInNamBySampleList(samples);
        sampleList.forEach(x -> System.out.println(x.toString()));
    }
}
