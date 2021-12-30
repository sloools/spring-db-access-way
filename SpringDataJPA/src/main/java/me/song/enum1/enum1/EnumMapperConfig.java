package me.song.enum1.enum1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class EnumMapperConfig {

    @Bean
    public EnumMapperFactory createEnumMaperFactory(){
        EnumMapperFactory enumMapperFactory = new EnumMapperFactory(new LinkedHashMap<>());
        enumMapperFactory.put("JOB", Job.class); // Job Enum클래스를 JOB이라는 key로 설정하여 factory에 등록
        enumMapperFactory.put("NATIONAL", National.class);
        return enumMapperFactory;
    }
}
