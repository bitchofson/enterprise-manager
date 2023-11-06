package ru.vsu.remezov.presentation.view.console;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;


import java.util.Scanner;

public class EmployeeCreatorMenuManager implements MenuManager {

    private final EmployeeController employeeController;

    private final DepartmentController departmentController;
    private final Scanner input;

    public EmployeeCreatorMenuManager(EmployeeController employeeController, DepartmentController departmentController, Scanner input) {
        this.employeeController = employeeController;
        this.departmentController = departmentController;
        this.input = input;
    }

    @Override
    public void show() {
        try {

            System.out.print("Введите ФИО: ");
            String fullName = input.nextLine();
            System.out.print("Введите возраст: ");
            int age = input.nextInt();
            input.nextLine();
            System.out.print("Введите заработную плату: ");
            int salary = input.nextInt();
            input.nextLine();
            System.out.println("Введите id отдела: ");
            departmentController.findAllDepartments().forEach(System.out::println);
            int id = input.nextInt();
            input.nextLine();

            employeeController.createEmployee(Employee.builder()
                    .fullName(fullName)
                    .age(age)
                    .salary(salary)
                    .department(Department.builder().id(id).build()).build());

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
