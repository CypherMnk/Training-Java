package com.training.dao;

import java.util.List;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.training.model.Employee;
import com.training.util.EmployeeHibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override public List<Employee> findAll() { return read(s -> s.createQuery("from Employee", Employee.class).list()); }
    @Override public Employee findById(int id) { return read(s -> s.createQuery("from Employee where id = :id", Employee.class).setParameter("id", id).uniqueResult()); }
    @Override public List<Employee> findByDepartment(String department) { return read(s -> s.createQuery("from Employee where department = :department", Employee.class).setParameter("department", department).list()); }
    @Override public List<Employee> salaryGreaterThan(double salary) { return read(s -> s.createQuery("from Employee where salary > :salary", Employee.class).setParameter("salary", salary).list()); }
    @Override public List<Employee> salaryBetween(double minimum, double maximum) { return read(s -> s.createQuery("from Employee where salary between :minimum and :maximum", Employee.class).setParameter("minimum", minimum).setParameter("maximum", maximum).list()); }
    @Override public List<Employee> nameContains(String text) { return read(s -> s.createQuery("from Employee where lower(name) like :text", Employee.class).setParameter("text", "%" + text.toLowerCase() + "%").list()); }
    @Override public List<Employee> departmentsIn(List<String> departments) { return read(s -> s.createQuery("from Employee where department in (:departments)", Employee.class).setParameterList("departments", departments).list()); }
    @Override public List<Employee> departmentsNotIn(List<String> departments) { return read(s -> s.createQuery("from Employee where department not in (:departments)", Employee.class).setParameterList("departments", departments).list()); }
    @Override public List<Employee> orderBySalaryDescending() { return read(s -> s.createQuery("from Employee order by salary desc", Employee.class).list()); }
    @Override public List<Employee> orderByName() { return read(s -> s.createQuery("from Employee order by name asc", Employee.class).list()); }
    @Override public List<Employee> itSalaryGreaterThan(double salary) { return read(s -> s.createQuery("from Employee where department = :department and salary > :salary", Employee.class).setParameter("department", "IT").setParameter("salary", salary).list()); }
    @Override public List<String> distinctDepartments() { return read(s -> s.createQuery("select distinct department from Employee", String.class).list()); }
    @Override public List<String> distinctDesignations() { return read(s -> s.createQuery("select distinct designation from Employee", String.class).list()); }
    @Override public Object[] salarySummary() { return read(s -> s.createQuery("select count(e), avg(e.salary), max(e.salary), min(e.salary), sum(e.salary) from Employee e", Object[].class).uniqueResult()); }
    @Override public List<Object[]> countByDepartment() { return read(s -> s.createQuery("select department, count(*) from Employee group by department", Object[].class).list()); }
    @Override public List<Object[]> departmentsAverageSalaryGreaterThan(double salary) { return read(s -> s.createQuery("select department, avg(salary) from Employee group by department having avg(salary) > :salary", Object[].class).setParameter("salary", salary).list()); }
    @Override public List<Object[]> nameDepartmentSalary() { return read(s -> s.createQuery("select name, department, salary from Employee", Object[].class).list()); }
    @Override public List<Employee> topHighestPaid(int count) { return read(s -> s.createQuery("from Employee order by salary desc", Employee.class).setMaxResults(count).list()); }
    @Override public List<Employee> aboveOverallAverageSalary() { return read(s -> s.createQuery("from Employee where salary > (select avg(salary) from Employee)", Employee.class).list()); }
    @Override public List<Employee> aboveOwnDepartmentAverageSalary() { return read(s -> s.createQuery("from Employee e where e.salary > (select avg(e2.salary) from Employee e2 where e2.department = e.department)", Employee.class).list()); }
    @Override public int increaseDepartmentSalary(String department, double percentage) { return write(s -> s.createQuery("update Employee set salary = salary * :factor where department = :department").setParameter("factor", 1 + percentage / 100).setParameter("department", department).executeUpdate()); }
    @Override public int deleteExperienceBelow(int experience) { return write(s -> s.createQuery("delete from Employee where experience < :experience").setParameter("experience", experience).executeUpdate()); }

    private <T> T read(Function<Session, T> action) {
        try (Session session = EmployeeHibernateUtil.getSessionFactory().openSession()) {
            return action.apply(session);
        }
    }

    private int write(Function<Session, Integer> action) {
        try (Session session = EmployeeHibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                int affected = action.apply(session);
                transaction.commit();
                return affected;
            } catch (RuntimeException exception) {
                transaction.rollback();
                throw exception;
            }
        }
    }
}
