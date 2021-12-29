package me.jdbc.dbaccess.service;

import lombok.RequiredArgsConstructor;
import me.jdbc.dbaccess.dto.Person;
import me.jdbc.dbaccess.repository.PersonRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring 5 이상에서부터,
 * Spring boot 2.xx  부터는
 *
 * 생성자 주입을 할 때,
 *
 * @Autowired를 생략할 수 있다.
 *
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public Person createPerson(Person person) {
        Person createdPerson = personRepository.createPerson(person);
        return createdPerson;
    }

	@Override
	@Transactional
	public Person readOnePerson(String name) {
		Person readOnePerson = personRepository.selectOnePerson(name);
		return readOnePerson;
	}

	@Override
	public List<Person> readAllPerson() {
		List<Person> allPerson = personRepository.selectAllPerson();
		return allPerson;
	}

	@Override
	public int deletePerson(int id) {
		int deletedCnt = personRepository.deletePerson(id);
		return deletedCnt;
	}

	@Override
	public Person patchPersonCompany(String updateColumn, String toBe, int id) {
		Person updatePerson = personRepository.patchPersonCompany(updateColumn, toBe, id);
		return updatePerson;
	}

	@Override
	public Person putPerson(Person person) {
		Person putPerson = personRepository.putPersonCompany(person);
		return putPerson;
	}
	
	
    
	
    
}
