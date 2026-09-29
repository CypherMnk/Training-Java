package com.training.honeapp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.training.dao.EmployeeDAO;
import com.training.dao.EmployeeDAOImpl;
import com.training.model.Employee;
import com.training.util.EmployeeHibernateUtil;

public class EmployeeHqlApp {

    private static final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                printMenu();
                choice = readInt(scanner, "Enter choice: ");
                runChoice(choice, scanner);
            } while (choice != 0);
        } finally {
            EmployeeHibernateUtil.shutdown();
        }
    }

    private static void runChoice(int choice, Scanner scanner) {
        switch (choice) {
        case 1:
            printEmployees(employeeDAO.findAll());
            break;
        case 2:
            System.out.println(employeeDAO.findById(readInt(scanner, "Employee ID: ")));
            break;
        case 3:
            printEmployees(employeeDAO.findByDepartment(readText(scanner, "Department: ")));
            break;
        case 4:
            printEmployees(employeeDAO.salaryGreaterThan(readDouble(scanner, "Minimum salary: ")));
            break;
        case 5:
            printEmployees(employeeDAO.salaryBetween(readDouble(scanner, "Minimum salary: "), readDouble(scanner, "Maximum salary: ")));
            break;
        case 6:
            printEmployees(employeeDAO.nameContains(readText(scanner, "Name search text: ")));
            break;
        case 7:
            printEmployees(employeeDAO.departmentsIn(Arrays.asList("IT", "Finance", "HR")));
            break;
        case 8:
            printEmployees(employeeDAO.departmentsNotIn(Arrays.asList("Sales", "Marketing")));
            break;
        case 9:
            System.out.println("Salary: high to low");
            printEmployees(employeeDAO.orderBySalaryDescending());
            System.out.println("Name: A to Z");
            printEmployees(employeeDAO.orderByName());
            break;
        case 10:
            printEmployees(employeeDAO.itSalaryGreaterThan(75000));
            break;
        case 11:
            System.out.println("Departments: " + employeeDAO.distinctDepartments());
            System.out.println("Designations: " + employeeDAO.distinctDesignations());
            break;
        case 12:
            printSummary(employeeDAO.salarySummary());
            break;
        case 13:
            printRows(employeeDAO.countByDepartment());
            break;
        case 14:
            printRows(employeeDAO.departmentsAverageSalaryGreaterThan(70000));
            break;
        case 15:
            printRows(employeeDAO.nameDepartmentSalary());
            break;
        case 16:
            printEmployees(employeeDAO.topHighestPaid(5));
            break;
        case 17:
            printEmployees(employeeDAO.aboveOverallAverageSalary());
            break;
        case 18:
            printEmployees(employeeDAO.aboveOwnDepartmentAverageSalary());
            break;
        case 19:
            System.out.println(employeeDAO.increaseDepartmentSalary(readText(scanner, "Department: "), 10)
                    + " employee salary record(s) increased by 10%.");
            break;
        case 20:
            System.out.println(employeeDAO.deleteExperienceBelow(readInt(scanner, "Delete employees with experience below: "))
                    + " employee record(s) deleted.");
            break;
        case 0:
            System.out.println("Application closed.");
            break;
        default:
            System.out.println("Enter a number from 0 to 20.");
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Employee HQL Assignment ---");
        System.out.println(" 1.All  2.By ID  3.By department  4.Salary greater than  5.Salary between");
        System.out.println(" 6.Name search  7.IN  8.NOT IN  9.Sorting  10.IT salary > 75000");
        System.out.println("11.DISTINCT  12.Aggregates  13.GROUP BY  14.HAVING  15.Projection");
        System.out.println("16.Top 5  17.Above overall average  18.Above department average");
        System.out.println("19.Increase department salary by 10%  20.Delete by experience  0.Exit");
    }

    private static void printEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        employees.forEach(System.out::println);
    }

    private static void printRows(List<Object[]> rows) {
        rows.forEach(row -> System.out.println(Arrays.toString(row)));
    }

    private static void printSummary(Object[] summary) {
        System.out.println("Total employees: " + summary[0]);
        System.out.println("Average salary: " + summary[1]);
        System.out.println("Highest salary: " + summary[2]);
        System.out.println("Lowest salary: " + summary[3]);
        System.out.println("Total salary: " + summary[4]);
    }

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readText(scanner, prompt));
            } catch (NumberFormatException exception) {
                System.out.println("Enter a whole number.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readText(scanner, prompt));
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid number.");
            }
        }
    }
}
