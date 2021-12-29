package me.song.enum1.controller;

import lombok.RequiredArgsConstructor;
import me.song.enum1.dto.Person;
import me.song.enum1.service.SampleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @PostMapping(value = "/person")
    public void insertPerson(@RequestBody Person person){
        sampleService.insertPerson(person);
    }

    @GetMapping(value = "/person/{id}")
    public Person readPerson(@PathVariable(value = "id") int id){
        return sampleService.readPerson(id);
    }

    @GetMapping(value = "/people/{name}")
    public List<Person> readPeople(@PathVariable String name){
        return sampleService.readPeopleByName(name);
    }

    @GetMapping(value = "/null")
    public String checkNull(){
        return null;
    }
}
