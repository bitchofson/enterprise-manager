package ru.vsu.remezov.presentation.view.console;

import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;

import java.util.Scanner;

public class DepartmentEditorMenuManager implements MenuManager {

    private final DepartmentController departmentController;
    private final EmployeeController employeeController;
    private final Scanner input;

    public DepartmentEditorMenuManager(DepartmentController departmentController, EmployeeController employeeController, Scanner input) {
        this.departmentController = departmentController;
        this.employeeController = employeeController;
        this.input = input;
    }

    @Override
    public void show() {
        try {
            System.out.println("Выберите из представленного списка id отдела, которого хотите изменить: ");
            departmentController.findAllDepartments().forEach(System.out::println);
            String id = input.nextLine();
            if (departmentController.isPresent(id)) {
                editorPage(id);
            } else {
                System.out.println("Такого id нет!");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void editorPage(String id) {
        try {

            System.out.println("(1) - Добавить сотрудника в отдел");
            System.out.println("(2) - Убрать сотрудника из отдела");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> addEmployeePage(id);
                case 2 -> deleteEmployeePage(id);
                default -> System.out.println("Ошибка: такого выбора нет!");
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void addEmployeePage(String idDepartment) {
        try {
            System.out.println("Выберите из представленного списка id сотрудников, которые будут добавленны в отдел: ");
            employeeController.findAllEmployees().forEach(System.out::println);
            String[] ids = input.nextLine().split(",");
            departmentController.addEmployeesToDepartment(idDepartment, employeeController.getEmployees(ids));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void deleteEmployeePage(String idDepartment) {
        try {
            System.out.println("Выберите из представленного списка id сотрудников, которые будут убраны из этого отдела: ");
            employeeController.findAllEmployees().forEach(System.out::println);
            String[] ids = input.nextLine().split(",");
            departmentController.deleteEmployeesFromDepartment(idDepartment, employeeController.getEmployees(ids));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
