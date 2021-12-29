package me.song.transactional;

import java.util.List;

public interface SampleServiceInterface {

    int createSampleMethod(Sample sample) throws MyException;
    Sample findSampleByName(String name);
    List<Sample> findAllSampleByName(String name);
}
