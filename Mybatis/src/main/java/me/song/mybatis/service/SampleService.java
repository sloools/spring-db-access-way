package me.song.mybatis.service;

import lombok.RequiredArgsConstructor;
import me.song.mybatis.entity.Sample;
import me.song.mybatis.mapper.SampleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleMapper sampleMapper;

    public Sample findSampleByName(String name){
        Sample sample = sampleMapper.findSampleByName(name);
        return sample;
    }

    public List<Sample> findAllSampleByName(String name){
        List<Sample> samples = sampleMapper.findAllSampleByName(name);
        return samples;
    }
}
