package com.training.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    private String department;
    private String designation;
    private double salary;
    private int experience;
    private String city;
    private int age;
    private String email;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }
    public int getExperience() { return experience; }
    public String getCity() { return city; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public LocalDate getJoiningDate() { return joiningDate; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setDesignation(String designation) { this.designation = designation; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setExperience(int experience) { this.experience = experience; }
    public void setCity(String city) { this.city = city; }
    public void setAge(int age) { this.age = age; }
    public void setEmail(String email) { this.email = email; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + department + " | " + designation
                + " | " + salary + " | " + experience + " years | " + city + " | age " + age;
    }
}
