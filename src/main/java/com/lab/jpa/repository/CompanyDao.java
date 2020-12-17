package com.lab.jpa.repository;

import com.lab.jpa.entities.Club;
import com.lab.jpa.entities.Salary;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        Session session = null;
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

    public List quaryAllClubs() {
        List list = getSession().createQuery("from Club c").list();
        return list;
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

    public List quaryCountAllEmps() {
        List list = getSession().createQuery("SELECT COUNT(*) AS count FROM Employee e ").list();
        return list;
    }

}
