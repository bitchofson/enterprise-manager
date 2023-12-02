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



            if (validateProperties(fullName, age, salary, id)) employeeController.createEmployee(Employee.builder()
                    .fullName(fullName)
                    .age(age)
                    .salary(salary)
                    .department(Department.builder().id(id).build()).build());

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private boolean validateProperties(String name, int age, int salary, int id) {
        if (name.isEmpty()) {
            System.err.println("Ошибка: Введена пустая строка!");
            return false;
        }

        if (age < 14) {
            System.err.println("Ошибка: Возраст сотрудника не может быть меньше 14 лет!");
            return false;
        }

        if (salary <= 0) {
            System.err.println("Ошибка: Заработная плата не может быть равна либо быть меньше нуля!");
            return false;
        }

        if (!departmentController.isExist(id)) {
            System.err.println("Ошибка: Введен некоректный id!");
            return false;
        }

        return true;
    }
}
