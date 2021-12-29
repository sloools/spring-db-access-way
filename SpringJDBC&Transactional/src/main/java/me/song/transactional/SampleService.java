package me.song.transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService implements SampleServiceInterface{

    private final SampleRepository sampleRepository;

    @Transactional(rollbackFor = MyException.class)
    @Override
    public int createSampleMethod(Sample sample) throws MyException {
        int rowCnt = sampleRepository.CreateSample(sample);
        try {


            if (rowCnt != 0) {
                log.info("Create Success !");
            } else {
                log.info("Create fail !");
            }


        }catch (Exception e){
            System.out.println("Exception 발생하므로 롤백합니다.");
        }

        return rowCnt;
    }

    @Transactional(readOnly = true)
    public Sample findSampleByName(String name){
        System.out.println("Sample Method 2");

        return sampleRepository.findOneSampleByName(name);
    }

    @Override
    public List<Sample> findAllSampleByName(String name) {
        return sampleRepository.findAllSampleByName(name);
    }
}
