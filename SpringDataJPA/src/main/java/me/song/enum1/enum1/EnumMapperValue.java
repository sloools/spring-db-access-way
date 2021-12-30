package me.song.enum1.enum1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
public class EnumMapperValue {
    private String code;
    private String title;

    /**
     *
     * @param enumMapper
     * 인터페이스를 생성자의 파라메터로 넣어준다.
     * 클래스를 생성할 때 해당 인터페이스를 상속받은 구현체가 구현한 값을 받아서 맴버변수를 초기화 해준다.
     * 이 프로젝트에서는 EnumMapperConfig에서 EnumMapperFactory를 생성하고 등록할 때 이 클래스를 사용하여 등록한다.
     * Config파일에서 초기화 하기 때문에 bean을 초기화 하는 컴파일 과정에서 이 클래스를 사용하여 EnumMapperFactory를 생성한다.
     */
    public EnumMapperValue(EnumMapper enumMapper){
        this.code = enumMapper.getCode();
        this.title = enumMapper.getTitle();
    }

//    public void constructTest(){
//        System.out.println("====================");
//        System.out.println(em.getClass());
//        System.out.println(em.getClass().toString());
//    }
}
