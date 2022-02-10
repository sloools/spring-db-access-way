package me.song.jpqlexample.querydsl;

import com.querydsl.core.Tuple;
import me.song.jpqlexample.entity.CustomerInfo;
import me.song.jpqlexample.entity.Sample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleRepositoryTest {

    @Autowired
    SampleJpaRepository sampleJpaRepository;

//    @Test
    @DisplayName("QueryDsl Find By name Order By Id ASC Test")
    public void findAllByNameOrderByIdASCTest(){
        for(Sample sample :sampleJpaRepository.findAllByNameOrderById("song")){
            System.out.println(sample.toString());
        }
    }

//    @Test
    @DisplayName("QueryDsl Find By name Order By Id DESC Test")
    public void findAllByNameOrderByIdDESCTest(){
        for(Sample sample :sampleJpaRepository.findAllByNameOrderByIdDesc("song")){
            System.out.println(sample.toString());
        }
    }

//    @Test
    @DisplayName("QueryDsl Find Customer Info Test")
    public void findCustomerInfoTest(){
        for(CustomerInfo customerInfo :sampleJpaRepository.findCustomerInfo()){
            System.out.println(customerInfo.toString());
        }
    }

//    @Test
    @DisplayName("QueryDsl Find Customer with Names Test")
    public void findCustomerWithNamesTest(){
        sampleJpaRepository.findNamesAndJobByJob("Programmer").
                stream().forEach(x ->
                                System.out.println(x.toString())
                        );
    }

    @Test
    @DisplayName("Dynamic Query By name")
    public void dynamicQueryBynameTest(){
        sampleJpaRepository.findNamesDynamicQuery("Song", null)
                .stream().forEach(x -> System.out.println(x.toString()));
    }

    @Test
    @DisplayName("Dynamic Query By job")
    public void dynamicQueryByJobTest(){
        sampleJpaRepository.findNamesDynamicQuery("", "SW")
                .stream().forEach(x -> System.out.println(x.toString()));
    }

    @Test
    @DisplayName("Dynamic Query By name And job")
    public void dynamicQueryByNameAndJobTest(){
        sampleJpaRepository.findNamesDynamicQuery("Song", "SW")
                .stream().forEach(x -> System.out.println(x.toString()));
    }
}
