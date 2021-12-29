package me.song.enum1.service;

import lombok.RequiredArgsConstructor;
import me.song.enum1.dto.Person;
import me.song.enum1.repository.SampleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    public void insertPerson(Person person){
        Person newPerson = Person.builder()
                .name(person.getName())
                .age(person.getAge())
                .job(person.getJob())
                .build();

        sampleRepository.save(newPerson);
    }

    @Transactional(readOnly = true)
    public Person readPerson(int id){
        Optional<Person> personOptional = sampleRepository.findById(id);
        return personOptional.orElse(null);
    }

    public List<Person> readPeopleByName(String name){
        Optional<List<Person>> people = sampleRepository.findAllByName(name);
        return people.orElse(Collections.emptyList());
    }
}
