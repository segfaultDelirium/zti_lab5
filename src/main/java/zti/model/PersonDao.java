package zti.model;

import org.springframework.stereotype.Component;

import java.util.List;


public interface PersonDao {

    public List<Person> getPeople();
    public Person getPersonByID(int id);
    public int save(Person person);
    public int update(int id);
    public int delete(int id);

}