package zti.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "saySimpleController")
@RequestMapping("/labs")
public class SimpleController {

    @RequestMapping("/lab01")
    public ModelAndView lab01() {
        Map<String, String> modelData = new HashMap<String, String>();
        modelData.put("msg", "Witaj, świecie!");
        return new ModelAndView("script01.jsp", modelData);
    }

    @RequestMapping("/lab02")
    public String lab02(@RequestParam(defaultValue="Anonimee") String name, Model model) {
        //Map<String, String> modelData = new HashMap<String, String>();
        //modelData.put("msg", "Witaj, świecie!");
        model.addAttribute("mess","Witaj " + name + "!");
        //return new ModelAndView("script02.jsp", modelData);
        return "script02.jsp" ;
    }
}