package com.lab.jpa.controller;

import com.lab.jpa.entities.Club;
import com.lab.jpa.entities.Employee;
import com.lab.jpa.repository.CompanyDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private CompanyDao dao;

     @GetMapping(value = {"/"})
    public String read(Model model) {
        List emp_list = dao.quaryAllEmps();
        List dept_list = dao.quaryAllDepts();
        List club_list = dao.quaryAllClubs();
        List empCount_list=dao.quaryCountAllEmps();
        Employee emp = new Employee();
        
        model.addAttribute("emp_list", emp_list);
        model.addAttribute("dept_list", dept_list);
        model.addAttribute("club_list", club_list);
        model.addAttribute("empCount_list", empCount_list);
        model.addAttribute("emp", emp);
        
        return "emp_page";
    }
    
    @PostMapping(value = {"/"})
   // @ResponseBody
    public String create(@ModelAttribute("emp") Employee emp,
            @RequestParam Integer[] clubIds) {
        
        if(clubIds != null) {
            for(Integer id : clubIds) {
                Club club = dao.getClub(id);
                emp.getClubs().add(club);
            }
        }
        dao.saveEmp(emp);
        return "redirect: ./";
 //       return emp.toString();
    }
}
