package me.song.mybatis.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Sample {

    Integer id;
    String name;
    Integer age;

}
