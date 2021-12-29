package me.song.enum1.enum1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 새로운 Enum 종류 추가, 항목 조회 등 관리하는 클래스
 */
public class EnumMapperFactory {
    private Map<String, List<EnumMapperValue>> factory;

    EnumMapperFactory(Map<String, List<EnumMapperValue>> factory){
        this.factory = factory;
    }

    // 새로운 Enum 종류 추가
    public void put(String key, Class<? extends EnumMapper> e){
        factory.put(key, toEnumValues(e));
    }

    // 특정 Enum 항목 조회
    public List<EnumMapperValue> get(String key){
        return factory.get(key);
    }

    // eNum 항목들에 순차적으로 접근하여 code와 title을 변수로 갖는 EnumMapper 객체로 생성하여 List로 받아옴
    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapper> e){
        return Arrays.stream(e.getEnumConstants())
                        .map(EnumMapperValue::new)
                        .collect(Collectors.toList());
    }
}
