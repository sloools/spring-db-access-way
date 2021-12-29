package me.jdbc.dbaccess.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jdbc.dbaccess.dto.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    //CREATE
    public Person createPerson(Person person){

        String query = "INSERT INTO GS27 (NAME, company, job, age, phoneNumber) VALUES(?,?,?,?,?)";

        int rowCnt = jdbcTemplate.update(query, person.getName(), person.getWorkAt(), person.getSpeciality(), person.getAge(), person.getPhoneNumber());
        log.info(query + "{" + person + "} updated row cnt : " + rowCnt);

        return person;
    }

	public String findNameById(int id){
		String findQuery = "SELECT name FROM GS27 WHERE id = ? ";

		String result = jdbcTemplate.queryForObject(findQuery, String.class, id); // queryForObject 는 하나의 도메인 객체를 리턴하거나 하나의 컬럼을 받을 때 사용
		System.out.println(result);
		return result;
	}
    
    // READ ONE
    public Person selectOnePerson(String name) {
    	String query = "SELECT * FROM GS27 WHERE name = ?";

    	Person readOnePerson = jdbcTemplate.queryForObject(query, new RowMapper<Person>() {
	    															public Person mapRow(ResultSet rs, int rowNum) throws SQLException{
	    																Person readPerson = new Person();
	    																readPerson.setId(rs.getInt("id"));
	    																readPerson.setName(rs.getString("name"));
	    																readPerson.setWorkAt(rs.getString("company"));
	    																readPerson.setSpeciality(rs.getString("job"));
	    																readPerson.setPhoneNumber(rs.getString("phoneNumber"));
	    																readPerson.setAge(rs.getInt("age"));
																		return readPerson;
	    															}
    															}, name);
    }
    
    // READ ALL
    public List<Person> selectAllPerson(){
    	String query = "SELECT * FROM GS27 ORDER BY id";
    	
    	List<Person> personList = jdbcTemplate.query(query, new RowMapper<Person>() { // RowMapper : ResultSet의 row 한 개만 받기 위해 사용
															public Person mapRow(ResultSet rs, int rowNum) throws SQLException{
																Person readPerson = new Person();
																readPerson.setId(rs.getInt("id"));
																readPerson.setName(rs.getString("name"));
																readPerson.setWorkAt(rs.getString("company"));
																readPerson.setSpeciality(rs.getString("job"));
																readPerson.setPhoneNumber(rs.getString("phoneNUmber"));
																readPerson.setAge(rs.getInt("age"));
																return readPerson;
															}
														});
    	return personList;
    }

    // DELETE
    public int deletePerson(int id) {
    	String query = "DELETE FROM GS27 WHERE id = ?";
    	
    	int rowCnt = jdbcTemplate.update(query, id);
    	
    	return rowCnt;
    }
    
    // PATCH
    public Person patchPersonCompany(String updateColumn, String toBe, int id) {
    	String query = "UPDATE GS27 SET " + updateColumn + "= ? WHERE id = ?";
    	
    	int rowCnt = jdbcTemplate.update(query, toBe, id);
    	Person updatedPerson = selectOnePersonById(id);
    	log.info(query + "{" + updatedPerson + "} updated row cnt : " + rowCnt);
    	return updatedPerson;
    }
    
    // PUT
    public Person putPersonCompany(Person person) {
    	String query = "INSERT INTO gs27 (NAME, company, job, age, phoneNumber) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE company=?, job=?, age=?, phoneNumber=?"; // upsert
    	int rowCnt = jdbcTemplate.update(query, person.getName(), person.getWorkAt(), person.getSpeciality(), person.getAge(), person.getPhoneNumber(), person.getWorkAt(), person.getSpeciality(), person.getAge(), person.getPhoneNumber());
    	Person putPerson = selectOnePerson(person.getName());
    	log.info(query + "{" + putPerson + "} updated row cnt : " + rowCnt);
    	
    	return putPerson;
    }

    public Person selectOnePersonById(int id) {
    	String query = "SELECT * FROM GS27 WHERE id = ?";

    	Person readOnePerson = jdbcTemplate.queryForObject(query, new RowMapper<Person>() {
	    															public Person mapRow(ResultSet rs, int rowNum) throws SQLException{
	    																Person readPerson = new Person();
	    																readPerson.setId(rs.getInt("id"));
	    																readPerson.setName(rs.getString("name"));
	    																readPerson.setWorkAt(rs.getString("company"));
	    																readPerson.setSpeciality(rs.getString("job"));
	    																readPerson.setPhoneNumber(rs.getString("phoneNUmber"));
	    																readPerson.setAge(rs.getInt("age"));
																		return readPerson;
	    															}
    															}, id);

    	return readOnePerson;
    }
}
