package me.song.mybatis.mapper;

import me.song.mybatis.entity.Sample;
import me.song.mybatis.entity.SampleReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Mapper 를 사용하여 Spring IOC 컨테이너에 Bean으로 등록한다.
 */
@Mapper
public interface SampleMapper {

    Sample findSampleByName(String name);

    List<Sample> findAllSampleByName(String name);

    List<Sample> findAllSampleInName(List<String> names);

    List<Sample> findAllSampleInNameBySampleList(List<SampleReq> sampleReqs);
}
