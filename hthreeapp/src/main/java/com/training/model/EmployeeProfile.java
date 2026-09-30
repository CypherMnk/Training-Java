package com.training.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_profiles")
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int profileId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "pan_number")
    private String panNumber;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;

    public EmployeeProfile() {
    }

    public EmployeeProfile(String phone, String panNumber) {
        this.phone = phone;
        this.panNumber = panNumber;
    }

    public int getProfileId() { return profileId; }
    public String getPhone() { return phone; }
    public String getPanNumber() { return panNumber; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    @Override
    public String toString() {
        return "EmployeeProfile{profileId=" + profileId + ", phone='" + phone + "', panNumber='" + panNumber + "'}";
    }
}