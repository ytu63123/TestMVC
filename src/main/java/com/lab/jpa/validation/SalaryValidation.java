//package com.lab.jpa.validation;
//
//import com.lab.jpa.entities.Department;
//import com.lab.jpa.entities.Employee;
//import com.lab.jpa.entities.Salary;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//@Component
//public class SalaryValidation implements Validator{
//
//    @Override
//    public boolean supports(Class<?> type) {
//        return Salary.class.isAssignableFrom(type);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        Salary salary = (Salary)o;
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salary.money", "salary.money.empty");
//        if(salary.getMoney() != null && salary.getMoney() < 40000) {
//            errors.rejectValue("salary.money", "salary.money.notenough");
//        }
//        
//    }
//    
//}
