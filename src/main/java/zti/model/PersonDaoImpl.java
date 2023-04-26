package zti.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    @Qualifier("dbDataSource")

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    public PersonDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Person> getPeople() {

        List<Person> people = new ArrayList<Person>();
        String query = "select id, fname, lname, email, city, tel from person";
        //jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String,Object>> personRows = jdbcTemplate.queryForList(query);

        for(Map<String,Object> personRow : personRows){
            Person person = new Person();
            person.setId(Integer.parseInt(String.valueOf(personRow.get("id"))));
            person.setFname(String.valueOf(personRow.get("fname")));
            person.setLname(String.valueOf(personRow.get("lname")));
            person.setCity(String.valueOf(personRow.get("city")));
            person.setEmail(String.valueOf(personRow.get("email")));
            person.setTel(String.valueOf(personRow.get("tel")));
            people.add(person);
        }
        return people;
    }

    public Person getPersonByID (int id) {
        String sql = "SELECT * FROM person WHERE id=?" ;
        //jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql,  new Object[]{id}, new BeanPropertyRowMapper<Person>(Person.class));
    }

    public int delete(int id) {
        String sql = "DELETE FROM person WHERE id=?" ;
        //jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update(sql);
    }

    public int save(Person person) {
        String sql = "" ;
        //jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update(sql);
    }

    public int update(int id) {
        String sql = "" ;
        //jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update(sql);
    }



}


