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

    private Session session=null ;

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

    public List quaryAllDepts() {
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

    public List quaryAllClubs() {
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

    public String quaryClubName(Integer id) {
        Club club = (Club) getSession().get(Club.class, id);
        club.getEmployees().stream().filter(a -> a.getClubs().equals("A")).count();
        return club.getName();
    }

    public Long quaryClubCount(Integer id) {
        Club club = (Club) getSession().get(Club.class, id);
        return club.getEmployees().stream().count();
    }

    public List quaryAllEmps() {
        List list = getSession().createQuery("from Employee e").list();
        return list;
    }

    public List quaryCountAllEmps() {
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

    public List quaryAllSalaries() {
        List list = getSession().createQuery("from Salary s").list();
        return list;
    }

    public List quarySumSalaries() {
        List list = getSession().createQuery("SELECT SUM(money) AS sum FROM Salary s ").list();
        return list;
    }

    public List quaryAvgSalaries() {
        List list = getSession().createQuery("SELECT AVG(money) AS avg FROM Salary s ").list();
        return list;
    }

    public String quarySalaryName(Integer id) {
        Salary s = (Salary) getSession().get(Salary.class, id);
        return s.getEmployee().getName();
    }

    public Integer quarySalaryByID(Integer id) {
        Salary s = (Salary) getSession().get(Salary.class, id);
        return s.getEmployee().getSalary().getMoney();
    }

}
