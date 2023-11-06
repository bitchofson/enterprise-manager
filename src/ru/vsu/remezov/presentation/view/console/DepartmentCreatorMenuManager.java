package ru.vsu.remezov.presentation.view.console;

import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;

import java.util.ArrayList;
import java.util.Scanner;

public class DepartmentCreatorMenuManager implements MenuManager {

    private final DepartmentController departmentController;
    private final EmployeeController employeeController;
    private final Scanner input;

    public DepartmentCreatorMenuManager(DepartmentController departmentController, EmployeeController employeeController, Scanner input) {
        this.departmentController = departmentController;
        this.employeeController = employeeController;
        this.input = input;
    }


    @Override
    public void show() {
        try {
            System.out.print("Введите название отдела: ");
            String name = input.nextLine();
            if (employeeController.findAllEmployees().size() != 0) {
                System.out.println("Выберите из представленного списка id сотрудников, которые будут добавленны в этот отдел: ");
                employeeController.findAllEmployees().forEach(System.out::println);
                String[] ids = input.nextLine().split(",");
                departmentController.createDepartment(name, employeeController.getEmployees(ids));
            } else {
                departmentController.createDepartment(name, new ArrayList<>());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}