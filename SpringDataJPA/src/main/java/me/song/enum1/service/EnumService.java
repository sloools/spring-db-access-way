package me.song.enum1.service;

import lombok.RequiredArgsConstructor;
import me.song.enum1.enum1.EnumMapperFactory;
import me.song.enum1.enum1.EnumMapperValue;
import me.song.enum1.enum1.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnumService {

    private final EnumMapperFactory enumMapperFactory;

    public void getEnum(String key){

        List<EnumMapperValue> enumMapperValueList = enumMapperFactory.get(key);

        for(EnumMapperValue value : enumMapperValueList){
            System.out.println(value.getCode());
            System.out.println(value.getTitle());
        }
    }


}
