package com.training.model;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private BigDecimal salary;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeProfile profile;

    public Employee() {
    }

    public Employee(String employeeName, String email, BigDecimal salary) {
        this.employeeName = employeeName;
        this.email = email;
        this.salary = salary;
    }

    public int getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getEmail() { return email; }
    public BigDecimal getSalary() { return salary; }
    public EmployeeProfile getProfile() { return profile; }

    public void setProfile(EmployeeProfile profile) {
        this.profile = profile;
        profile.setEmployee(this);
    }

    @Override
    public String toString() {
        return "Employee{employeeId=" + employeeId + ", employeeName='" + employeeName + "', email='" + email
                + "', salary=" + salary + "}";
    }
}