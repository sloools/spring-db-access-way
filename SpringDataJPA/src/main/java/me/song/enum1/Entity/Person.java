package me.song.enum1.Entity;

import lombok.*;
import me.song.enum1.config.EnumConverter;
import me.song.enum1.enum1.Job;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="person")
public class Person {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "age")
    Integer age;

//    @Enumerated(EnumType.STRING)
    @Convert(converter = EnumConverter.class)
    Job job; // DB에 insert 시, JOB enum에 등록된 값이 아니면 에러를 발생한다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

//    public Person(String name, Integer age, Job job){
//        this.name = name;
//        this.age = age;
//        this.job = job;
//    }
}
