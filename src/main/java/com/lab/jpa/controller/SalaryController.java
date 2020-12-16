package com.lab.jpa.controller;

import com.lab.jpa.repository.CompanyDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private CompanyDao dao;

    @RequestMapping(value = {"/"})
    public String read(Model model) {
        List salary_list = dao.quaryAllSalaries();
        List salarySum_list=dao.quarySumSalaries();
        List salaryAvg_list=dao.quaryAvgSalaries();
        model.addAttribute("salary_list", salary_list);
        model.addAttribute("salarySum_list", salarySum_list);
         model.addAttribute("salaryAvg_list", salaryAvg_list);
        return "salary_page";
    }
}
