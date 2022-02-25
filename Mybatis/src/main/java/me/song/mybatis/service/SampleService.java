package me.song.mybatis.service;

import lombok.RequiredArgsConstructor;
import me.song.mybatis.entity.Sample;
import me.song.mybatis.entity.SampleReq;
import me.song.mybatis.mapper.SampleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleMapper sampleMapper;

    /* 하나의 조건으로 한 row 결과 받아오기 */
    public Sample findSampleByName(String name){
        Sample sample = sampleMapper.findSampleByName(name);
        return sample;
    }

    /* 하나의 조건으로 전체 row 결과 받아오기 */
    public List<Sample> findAllSampleByName(String name){
        List<Sample> samples = sampleMapper.findAllSampleByName(name);
        return samples;
    }

    /* list 조건으로 전체 row 결과 받아오기 */
    public List<Sample> findAllSampleInName(List<String> names){
        List<Sample> samples = sampleMapper.findAllSampleInName(names);
        return samples;
    }

    /* 객체 list 조건으로 전체 row 결과 받아오기 */
    public List<Sample> findAllSampleInNamBySampleList(List<SampleReq> names){
        List<Sample> samples = sampleMapper.findAllSampleInNameBySampleList(names);
        return samples;
    }
}
