package me.jdbc.dbaccess.service;

import java.util.List;

import me.jdbc.dbaccess.dto.Person;

public interface PersonService {
    //create
    Person createPerson(Person person);

    //readOne
    Person readOnePerson(String name);

    //readAll
    List<Person> readAllPerson();

    //delete
    int deletePerson(int id);

    //patch
    Person patchPersonCompany(String updateColumn, String toBe, int id);

    //put
    Person putPerson(Person person);
}
