package me.song.jpqlexample.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="sample")
@NamedQuery(
        name = "Sample.findByage" , // 엔티티명.정적쿼리명으로 이름 규칙을 정하는 것이 좋음
        query = "select s from sample s where s.age = :age"
)
// 여러 정적 쿼리를 한 엔티티에 정의하는 것도 가능
public class Sample {
    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "age")
    Integer age;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
}
