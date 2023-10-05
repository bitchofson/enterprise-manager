package ru.vsu.remezov.presentation.view;

import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {

    EmployeeController employeeController;
    DepartmentController departmentController;
    Scanner input;
    public ConsoleView(EmployeeController employeeController, DepartmentController departmentController) {
        this.employeeController = employeeController;
        this.departmentController = departmentController;
        this.input = new Scanner(System.in);
    }

    public void view() {
        boolean done = false;
        int choice;

        while (!done) {
            try {
                mainMenu();
                choice = input.nextInt();
                input.nextLine();
                done = handleUserInput(choice);
            } catch (NullPointerException exception) {
                exception.printStackTrace();
            } catch (Exception exception) {
                System.out.println("Ошибка ввода! Введите корректное число.\n");
                input.next();
            }
        }
    }

    private boolean handleUserInput(final int choice) {
        switch (choice) {
            case 1 -> employeeInformation();
            case 2 -> departmentInformation();
            case 3 -> createEmployeeMenu();
            case 4 -> createDepartmentMenu();
            case 5 -> editorEmployeeMenu();
            case 6 -> editorDepartmentMenu();
            case 7 -> { return true; }
            default -> System.out.println("Введите корректное число в диапозоне от 1 до 7!\n");
        }

        return false;
    }

    private void employeeInformation() {
        employeeController.getAllEmployees().forEach(System.out::println);
    }

    private void departmentInformation() {
        departmentController.getAllDepartments().forEach(System.out::println);
        departmentController.getAllSalaryDepartments().forEach((key, value) -> System.out.println("Общая сумма зарплат для отдела " + key + " " + value));
    }

    private void createEmployeeMenu() {
        try {
            System.out.print("Введите ФИО: ");
            String fullName = input.nextLine();
            System.out.print("Введите возраст: ");
            String age = input.next();
            System.out.print("Введите заработную плату: ");
            int salary = input.nextInt();

            employeeController.createEmployee(fullName, age, salary);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void createDepartmentMenu() {
        try {
            System.out.print("Введите название отдела: ");
            String name = input.nextLine();
            if (employeeController.getAllEmployees().size() != 0) {
                System.out.println("Выберите из представленного списка id сотрудников, которые будут добавленны в этот отдел: ");
                employeeInformation();
                String[] ids = input.nextLine().split(",");
                departmentController.createDepartment(name, employeeController.getEmployees(ids));
            } else {
                departmentController.createDepartment(name, new ArrayList<>());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void editorEmployeeMenu() {

    }

    private void editorDepartmentMenu() {

    }
    private void mainMenu() {
        System.out.println("-----------------------------");
        System.out.println("\t\t\tГлавное Меню");
        System.out.println("-----------------------------");
        System.out.println("""
                (1) - Информация о сотрудниках
                (2) - Ифнормация об отделах
                (3) - Создать сотрудника
                (4) - Создать отдел
                (5) - Редактировать сотрудника
                (6) - Редактировать отдел
                (7) - Выход""");
        System.out.print("(?): ");
    }
}
