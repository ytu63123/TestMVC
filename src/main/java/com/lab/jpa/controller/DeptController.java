package com.lab.jpa.controller;

import com.lab.jpa.entities.Department;
import com.lab.jpa.repository.CompanyDao;
import com.lab.jpa.validation.DeptValidation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private CompanyDao dao;

    @Autowired
    private DeptValidation validation;

    @GetMapping(value = {"/", //查詢全部資料用
        "/{id}",//根據id查詢單筆使用(修改連結用)
        "/{delete}/{id}"})//根據id查詢單筆使用(給刪除連結用)
    public String read(Model model,
            @PathVariable Optional<Integer> id,
            @PathVariable Optional<String> delete) {
        String _method = "POST";
        List dept_list = dao.queryAllDepts();
        Department dept = new Department();
        if (id.isPresent()) {
            _method = "PUT";
            dept = dao.getDept(id.get());

            if (delete.isPresent() && delete.get().equalsIgnoreCase("delete")) {
                _method = "DELETE";
            }

        }
        model.addAttribute("_method", _method);
        model.addAttribute("dept_list", dept_list);
        model.addAttribute("dept", dept);
        return "dept_page";
    }

    @PostMapping(value = {"/"})
    public String create(@ModelAttribute("dept") Department dept, BindingResult result,Model model) {
        //數據驗證 @ModelAttribute("dept") 一定要加
        validation.validate(dept, result);
        if (result.hasErrors()) {
            model.addAttribute("_method", "POST");
            model.addAttribute("dept_list",dao.queryAllDepts());
            model.addAttribute("dept", dept);
             return "dept_page";
        }
        dao.saveDept(dept);
        return "redirect: ./";
    }

    @PutMapping(value = {"/"})
    public String update(@ModelAttribute("dept") Department dept) {

        dao.updateDept(dept);
        return "redirect: ./";
    }

    @DeleteMapping(value = {"/"})
    public String delete(@ModelAttribute("dept") Department dept) {
        dao.deleteDept(dept.getId());
        return "redirect: ./";
    }

}
