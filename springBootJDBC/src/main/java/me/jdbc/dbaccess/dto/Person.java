package me.jdbc.dbaccess.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;
    private String name;
    private String workAt;
    private String speciality;
    private int age;
    private String phoneNumber;
}
