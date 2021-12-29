package me.song.enum1.dto;

import lombok.*;
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

    @Enumerated(EnumType.STRING)
    Job job;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

//    public Person(String name, Integer age, Job job){
//        this.name = name;
//        this.age = age;
//        this.job = job;
//    }
}
