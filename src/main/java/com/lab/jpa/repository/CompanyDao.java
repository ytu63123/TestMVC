package com.lab.jpa.repository;

import com.lab.jpa.entities.Club;
import com.lab.jpa.entities.Department;
import com.lab.jpa.entities.Employee;
import com.lab.jpa.entities.Salary;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session = null;

    private Session getSession() {
//        if(session!=null){
//        return session;
//        }

        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public List queryAllDepts() {
        List list = getSession().createQuery("from Department d").list();
        return list;
    }

    public Department getDept(Integer id) {
        Department dept = (Department) getSession().get(Department.class, id);
        return dept;
    }

    @Transactional
    public void saveDept(Department dept) {
        getSession().persist(dept);
    }

    // 修改部門
    @Transactional
    public void updateDept(Department dept) {
        getSession().update(dept);
    }

    // 刪除部門
    @Transactional
    public void deleteDept(Integer id) {
        Department dept = (Department) getSession().get(Department.class, id);
        getSession().delete(dept);
    }

    public List queryAllClubs() {
        List list = getSession().createQuery("from Club c").list();
        return list;
    }

    public Club getClub(Integer id) {
        Club club = (Club) getSession().get(Club.class, id);
        return club;
    }

    @Transactional
    public void saveClub(Club club) {
        getSession().persist(club);
    }

    // 修改社團
    @Transactional
    public void updateClub(Club club) {
        getSession().update(club);
    }

    // 刪除社團
    @Transactional
    public void deleteClub(Integer id) {
        Club club = (Club) getSession().get(Club.class, id);
        getSession().delete(club);
    }

    public String queryClubName(Integer id) {
        Club club = (Club) getSession().get(Club.class, id);
        club.getEmployees().stream().filter(a -> a.getClubs().equals("A")).count();
        return club.getName();
    }

    public Long queryClubCount(Integer id) {
        Club club = (Club) getSession().get(Club.class, id);
        return club.getEmployees().stream().count();
    }

    public List queryAllEmps() {
        List list = getSession().createQuery("from Employee e").list();
        return list;
    }

    public List queryCountAllEmps() {
        List list = getSession().createQuery("SELECT COUNT(*) AS count FROM Employee e ").list();
        return list;
    }

    // 查詢單筆員工
    public Employee getEmp(Integer id) {
        Employee emp = (Employee) getSession().get(Employee.class, id);
        return emp;
    }

    // 新增員工
    @Transactional
    public void saveEmp(Employee emp) {
        getSession().persist(emp);
    }

    // 修改員工
    @Transactional
    public void updateEmp(Employee emp) {
        getSession().update(emp);
    }

    // 刪除員工
    @Transactional
    public void deleteEmp(Integer id) {
        Employee emp = (Employee) getSession().get(Employee.class, id);
        getSession().delete(emp);
    }

    public List queryAllSalaries() {
        List list = getSession().createQuery("from Salary s").list();
        return list;
    }

    public List querySumSalaries() {
        List list = getSession().createQuery("SELECT SUM(money) AS sum FROM Salary s ").list();
        return list;
    }

    public List queryAvgSalaries() {
        List list = getSession().createQuery("SELECT AVG(money) AS avg FROM Salary s ").list();
        return list;
    }

//    public String querySalaryName(Integer id) {
//        Salary s = (Salary) getSession().get(Salary.class, id);
//        return s.getEmployee().getName();
//    }
//
//    public Integer querySalaryByID(Integer id) {
//        Salary s = (Salary) getSession().get(Salary.class, id);
//        return s.getEmployee().getSalary().getMoney();
//    }

    public Salary getSalary(Integer id) {
        Salary salary = (Salary) getSession().get(Salary.class, id);
        return salary;
    }

    // 新增員工
    @Transactional
    public void saveSalary(Salary salary) {
        getSession().persist(salary);
    }

    // 修改薪水
    @Transactional
    public void updateSalary(Salary salary) {
        getSession().update(salary);
    }

}
