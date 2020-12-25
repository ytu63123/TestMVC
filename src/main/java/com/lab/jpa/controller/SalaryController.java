//package com.lab.jpa.controller;
//
//import com.lab.jpa.entities.Club;
//import com.lab.jpa.entities.Salary;
//import com.lab.jpa.repository.CompanyDao;
//import com.lab.jpa.validation.SalaryValidation;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/salary")
//public class SalaryController {
//
//    @Autowired
//    private CompanyDao dao;
//
//    @Autowired
//    private SalaryValidation validation;
//
//    @GetMapping(value = {"/", "/{id}"})
//    public String read(Model model, @PathVariable Optional<Integer> id) {
//        String _method = "PUT";
//        List salary_list = dao.queryAllSalaries();
//        List salarySum_list = dao.querySumSalaries();
//        List salaryAvg_list = dao.queryAvgSalaries();
//        Salary salary = dao.getSalary(1);
//        if (id.isPresent()&& id.get()!=1) {
//            _method = "PUT";
//            salary = dao.getSalary(id.get());
//
//        }
//        
//        model.addAttribute("_method", _method);
//        model.addAttribute("salary_list", salary_list);
//        model.addAttribute("salarySum_list", salarySum_list);
//        model.addAttribute("salaryAvg_list", salaryAvg_list);
//        model.addAttribute("salary", salary);
//
//        return "salary_page";
//    }
//
////    @PostMapping(value = {"/"})
////    public String create(@ModelAttribute("salary") Salary salary,
////            BindingResult result, Model model) {
////        // 數據驗證
////        validation.validate(salary, result);
////        if (result.hasErrors()) {
////            model.addAttribute("salary_list", dao.queryAllSalaries());
////            model.addAttribute("salarySum_list", dao.querySumSalaries());
////            model.addAttribute("salaryAvg_list", dao.queryAvgSalaries());
////            model.addAttribute("salary", salary);
////            return "salary_page";
////        }
////        return "salary_page";
////    }
//
//    @PutMapping(value = {"/"})
//    public String update(@ModelAttribute("salary") Salary salary) {
//        dao.updateSalary(salary);
//        return "redirect: ./";
//    }
//
//}
