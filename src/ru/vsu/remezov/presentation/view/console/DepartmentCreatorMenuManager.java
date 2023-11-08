package ru.vsu.remezov.presentation.view.console;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.presentation.controller.DepartmentController;
import java.util.Scanner;

public class DepartmentCreatorMenuManager implements MenuManager {

    private final DepartmentController departmentController;
    private final Scanner input;

    public DepartmentCreatorMenuManager(DepartmentController departmentController, Scanner input) {
        this.departmentController = departmentController;
        this.input = input;
    }


    @Override
    public void show() {
        try {

            System.out.print("Введите название отдела: ");
            String name = input.nextLine();
            if (validateString(name)) departmentController.createDepartment(Department.builder().name(name).build());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private boolean validateString(String value) {
        if (value.isEmpty()) {
            System.err.println("Ошибка: Введена пустая строка!");
            return false;
        }
        return true;
    }
}