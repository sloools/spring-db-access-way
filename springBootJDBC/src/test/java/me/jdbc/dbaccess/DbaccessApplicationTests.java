package me.jdbc.dbaccess;

import me.jdbc.dbaccess.dto.Person;
import me.jdbc.dbaccess.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DbaccessApplicationTests {

    @Autowired
    PersonRepository personRepository;

    @Test
    @DisplayName("이 테스트는, JDBC TEMPLATE을 사용하여, Person을 DB에 등록하는 테스트입니다.")
    void createPersonTest(){
        Person person = new Person();
        person.setAge(26);
        person.setWorkAt("TUNA TEAM");
        person.setSpeciality("PD");
        person.setPhoneNumber("010-4324-3465");
        person.setName("KIM MINJI");

        Person updatedPerson = personRepository.createPerson(person);
        assertThat(updatedPerson).isNotNull();

    }


}
