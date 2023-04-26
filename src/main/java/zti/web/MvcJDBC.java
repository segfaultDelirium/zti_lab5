package zti.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import zti.model.Person;
import zti.model.PersonDaoImpl;

@Controller(value = "MvcController")
@RequestMapping("/mvc")
public class MvcJDBC {

    @Autowired
    PersonDaoImpl personDao;

    @RequestMapping(value = "/list")
    public String getTable(Model model) {
        // logger.info("Start getAllEmployees.");
        List<Person> people = personDao.getPeople() ;
        model.addAttribute("table",people);
        return "list.jsp";
    }

    @RequestMapping(value = "/list2")
    public String getTable2(Model model) {
        // logger.info("Start getAllEmployees.");
        List<Person> people = personDao.getPeople() ;
        model.addAttribute("table",people);
        return "list.html";
    }

}