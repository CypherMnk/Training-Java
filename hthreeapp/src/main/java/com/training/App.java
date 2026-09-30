package com.training;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.training.model.Employee;
import com.training.model.EmployeeProfile;
import com.training.util.HibernateUtil;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            getEmployeeWithProfile(sessionFactory);
            insertEmployeeWithProfile(sessionFactory);
            getProfileWithEmployee(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    private static void getEmployeeWithProfile(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Employee employee = session.find(Employee.class, 1);
            if (employee == null || employee.getProfile() == null) {
                System.out.println("Employee ID 1 or its profile was not found.");
                return;
            }
            System.out.println(employee.getEmployeeId() + ", " + employee.getEmployeeName() + ", "
                    + employee.getEmail() + ", " + employee.getSalary() + ", "
                    + employee.getProfile().getPanNumber() + ", " + employee.getProfile().getPhone());
        }
    }

    private static void insertEmployeeWithProfile(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Employee employee = new Employee("Hari", "hari@gmail.com", new BigDecimal("300000.00"));
                EmployeeProfile profile = new EmployeeProfile("93939393", "AE392309");
                employee.setProfile(profile);

                session.persist(employee);
                transaction.commit();
                System.out.println("Employee with profile inserted successfully.");
            } catch (RuntimeException e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    private static void getProfileWithEmployee(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            EmployeeProfile profile = session.find(EmployeeProfile.class, 1);
            if (profile == null || profile.getEmployee() == null) {
                System.out.println("Profile ID 1 or its employee was not found.");
                return;
            }
            Employee employee = profile.getEmployee();
            System.out.println(employee.getEmployeeId() + ", " + employee.getEmployeeName() + ", "
                    + employee.getEmail() + ", " + employee.getSalary() + ", " + profile.getPanNumber() + ", "
                    + profile.getPhone());
        }
    }
}