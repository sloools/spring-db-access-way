package me.song.enum1.config;

import me.song.enum1.enum1.Job;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class EnumConverter implements AttributeConverter <Job, String> {

    /* 어떤 EnumType으로 DB에 저장할지 */
    @Override
    public String convertToDatabaseColumn(Job job) {
        if(job == null){
            return null;
        }
        return job.getCode(); // 코드로 저장
    }

    /* DB에서 읽힌 값을 어떻게 enum과 매칭시킬 지 */
    @Override
    public Job convertToEntityAttribute(String s) {
        if(s.isBlank()){
            return null;
        }
        return Stream.of(Job.values())
                .filter(job -> job.getCode().equals(s)) //
                .findFirst()
                .orElse(null);
    }
}
