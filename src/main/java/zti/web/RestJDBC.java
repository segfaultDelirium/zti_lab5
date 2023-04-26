package zti.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import zti.model.Person;
import zti.model.PersonDaoImpl;

@Controller(value = "JdbcController")
@RequestMapping("/rest")
public class RestJDBC {

    @Autowired
    PersonDaoImpl dao;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public @ResponseBody List<Person> getAllPerson() {
        // logger.info("Start getAllEmployees.");
        List<Person> people = dao.getPeople() ;
        return people ;
    }

    @RequestMapping(value= "/person/{id}", method= RequestMethod.GET)
    public @ResponseBody Person getPerson(@PathVariable int id) {
        Person entity = dao.getPersonByID(id);
        return entity;
    }

}