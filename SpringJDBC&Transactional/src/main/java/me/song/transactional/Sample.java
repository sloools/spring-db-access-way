package me.song.transactional;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sample {

    private Integer id;
    private String name;
    private Integer age;

    public String toString(){
        return "id : " + id + ", name :" + name + ", age : " + age;
    }
}
