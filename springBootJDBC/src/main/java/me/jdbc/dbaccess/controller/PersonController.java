package me.jdbc.dbaccess.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jdbc.dbaccess.dto.Person;
import me.jdbc.dbaccess.repository.PersonRepository;
import me.jdbc.dbaccess.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.awt.*;

//RESTFUL한 구조로 만드는 방법을 알려드릴게요!
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person createPerson(@RequestBody Person person){
        Person createdPerson = personService.createPerson(person);
        return createdPerson;
    }
    
    @GetMapping("/person/{name}")
    public Person readPerson(@PathVariable("name") String name) {
    	Person readOnePerson = personService.readOnePerson(name);
    	return readOnePerson;
    }
    
    @GetMapping("/persons")	
    public List<Person> readPersons() {
    	List<Person> readAllPerson = personService.readAllPerson();
    	return readAllPerson;
    }
    
    @DeleteMapping("/person/{id}")
    public String deletePerson(@PathVariable("id") int id) {
    	int row = personService.deletePerson(id);
    	
    	return row + "개의 로우가 삭제되었습니다.";
    }

    @PatchMapping("/person") // Partial Modify
    public Person patchPerson(@RequestParam("column") String column, @RequestParam("tobe") String tobe, @RequestParam("id") int id) {
    	Person modifiedPerson = personService.patchPersonCompany(column,tobe , id);
    	
    	return modifiedPerson;
    }
    
    @PutMapping("/person") // create and replace
    public Person putPerson(@RequestBody Person person) {
    	Person putPerson = null;
    	try {
    		putPerson = personService.putPerson(person);
		} catch (Exception e) {
			System.out.println(e);
		}
    	   	
    	return putPerson;
    }
}
