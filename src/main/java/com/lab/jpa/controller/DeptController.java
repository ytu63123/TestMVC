package com.lab.jpa.controller;

import com.lab.jpa.entities.Department;
import com.lab.jpa.repository.CompanyDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private CompanyDao dao;

    @GetMapping(value = {"/"})
    public String read( Model model) {  
        List dept_list = dao.quaryAllDepts();
        Department dept = new Department();
        model.addAttribute("dept", dept);
        model.addAttribute("dept_list", dept_list);
        return "dept_page";
    }

    @RequestMapping(value = {"/{id}"})
    public String get( Model model,@PathVariable(name = "id", required = true) Integer id) {  
        List dept_list = dao.quaryAllDepts();
        Department dept = dao.getDept(id);
        model.addAttribute("dept", dept);
        model.addAttribute("dept_list", dept_list);
        return "dept_page";
    }
    

    @PostMapping(value = {"/"})
    public String create(@ModelAttribute("dept") Department dept) {
        dao.saveDept(dept);
        return "redirect: ./";
    }
}
