package com.training.dao;

import java.util.List;

import com.training.model.Employee;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    List<Employee> findByDepartment(String department);
    List<Employee> salaryGreaterThan(double salary);
    List<Employee> salaryBetween(double minimum, double maximum);
    List<Employee> nameContains(String text);
    List<Employee> departmentsIn(List<String> departments);
    List<Employee> departmentsNotIn(List<String> departments);
    List<Employee> orderBySalaryDescending();
    List<Employee> orderByName();
    List<Employee> itSalaryGreaterThan(double salary);
    List<String> distinctDepartments();
    List<String> distinctDesignations();
    Object[] salarySummary();
    List<Object[]> countByDepartment();
    List<Object[]> departmentsAverageSalaryGreaterThan(double salary);
    List<Object[]> nameDepartmentSalary();
    List<Employee> topHighestPaid(int count);
    List<Employee> aboveOverallAverageSalary();
    List<Employee> aboveOwnDepartmentAverageSalary();
    int increaseDepartmentSalary(String department, double percentage);
    int deleteExperienceBelow(int experience);
}
