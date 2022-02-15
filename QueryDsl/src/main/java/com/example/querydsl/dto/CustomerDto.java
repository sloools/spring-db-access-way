package com.example.querydsl.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class CustomerDto {
    Integer id;
    String name;
    Integer age;
    Integer ordCnt;
}
