package me.song.transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SampleRepository {

    private final JdbcTemplate jdbcTemplate;

    public int CreateSample(Sample sample) throws MyException {


        String createQuery = "INSERT INTO sample VALUES (?, ?, ?)";

        int rowCnt = jdbcTemplate.update(createQuery, null ,sample.getName(), sample.getAge());

        log.info("updated row count : " + rowCnt);

        if(true){
            MyException ex = new MyException("My Exception 발생 !"); //noRollbackFor 옵션 확인을 위한 Exception발생
            throw ex;
        }

        return rowCnt;
    }

    public String findNameById(int id){
        String findQuery = "SELECT name FROM sample WHERE id = ? ";

        String result = jdbcTemplate.queryForObject(findQuery, String.class, id); // queryForObject 는 하나의 도메인 객체를 리턴하거나 하나의 컬럼을 받을 때 사용
        System.out.println(result);
        return result;
    }

    public Sample findOneSampleByName(String name){
        String findQuery = "SELECT * FROM sample WHERE name = ? ";

        /* jdbcTemplate에서 객체를 받아올 땐 파라메터를 RowMapper를 넣어준다. RowMapper를 사용하면 원하는 형태로 여러 컬럼을 받아올 수 있다*/
        /* JDBC에서는 쿼리를 조회하면 ResultSet으로 반환받았기 때문에 RowMapper형태를 사용하지 않아도 됐다. RowMapper는 파라메터로 ResultSet을 사용한다.*/

        Sample result2 = jdbcTemplate.queryForObject(findQuery, new RowMapper<Sample>() {
            @Override
            public Sample mapRow(ResultSet rs, int rowNum) throws SQLException { // rowNum 뭔지 찾아보기
                Sample sam = new Sample();

                sam.setId(rs.getInt("id"));
                sam.setName(rs.getString("name"));
                sam.setAge(rs.getInt("age"));

                return sam;
            }
        }, name);

        /* 아래는 위의 익명 클래스를 람다 형태로 바꾼 방법이다. */
        Sample result = jdbcTemplate.queryForObject(findQuery, (rs, count) ->
                new Sample(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                )
                , name);

        System.out.println(result.toString());
        return result;
    }

    public List<String> findAllNameByAge(int age){
        String findQuery = "SELECT name FROM sample WHERE age = ? ";

        List<String> result = jdbcTemplate.queryForList(findQuery, String.class , age);
//        System.out.println(jdbcTemplate.queryForObject(query, Sample.class));
        result.forEach(System.out::println);

//        result.forEach((key, value) -> System.out.println(key + " : " + value));

//        while(result.iterator().hasNext()){
//            log.info("select result : " + result.iterator().next());
//        }


        return result;
    }

    public List<Sample> findAllSampleByName(String name){
        String findQuery = "SELECT * FROM sample WHERE name = ? ";
        List<Sample> result = jdbcTemplate.query(findQuery, (rs, count) ->
                        new Sample(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("age")
                        )
                , name);

        result.forEach(System.out::println);

        return result;
    }

}
