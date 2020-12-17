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
        List salarySum_list = dao.quarySumSalaries();
        List salaryAvg_list = dao.quaryAvgSalaries();
        String salaryName1 = dao.quarySalaryName(1);
        String salaryName2 = dao.quarySalaryName(2);
        String salaryName3 = dao.quarySalaryName(3);
        String salaryName4 = dao.quarySalaryName(4);
        Integer salary1 = dao.quarySalaryByID(1);
        Integer salary2 = dao.quarySalaryByID(2);
        Integer salary3 = dao.quarySalaryByID(3);
        Integer salary4 = dao.quarySalaryByID(4);
        model.addAttribute("salary_list", salary_list);
        model.addAttribute("salarySum_list", salarySum_list);
        model.addAttribute("salaryAvg_list", salaryAvg_list);
        model.addAttribute("salaryName1", salaryName1);
        model.addAttribute("salaryName2", salaryName2);
        model.addAttribute("salaryName3", salaryName3);
        model.addAttribute("salaryName4", salaryName4);
        model.addAttribute("salary1", salary1);
        model.addAttribute("salary2", salary2);
        model.addAttribute("salary3", salary3);
        model.addAttribute("salary4", salary4);
        return "salary_page";
    }
}
