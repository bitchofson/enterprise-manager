package ru.vsu.remezov.presentation.view.console;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.presentation.controller.DepartmentController;


import java.util.Scanner;

public class DepartmentEditorMenuManager implements MenuManager {

    private final DepartmentController departmentController;
    private final Scanner input;

    public DepartmentEditorMenuManager(DepartmentController departmentController, Scanner input) {
        this.departmentController = departmentController;
        this.input = input;
    }

    @Override
    public void show() {
        try {
            System.out.println("Выберите из представленного списка, id отдела которого хотите изменить: ");
            departmentController.findAllDepartments().forEach(System.out::println);
            int id = input.nextInt();
            input.nextLine();
            if (validateId(id)) editorPage(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void editorPage(int id) {
        try {
            System.out.println("Изменить имя отдела (1)");
            System.out.println("Удалить отдел (2)");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> updatePage(id);
                case 2 -> deletePage(id);
                case 3 -> System.err.println("Ошибка: Введено неверное число!");
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void updatePage(int id) {
        try {
            System.out.println("\nВведите новое имя отдела");
            String nameNew = input.nextLine();

               if(validateString(nameNew)) departmentController.updateDepartment(
                        Department.builder().id(id).build(),
                        Department.builder().name(nameNew).build()
                );

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

    private boolean validateId(int value) {
        if (!departmentController.isPresent(value)) {
            System.err.println("Ошибка: Введен некоректный id!");
            return false;
        }
        return true;
    }

    private void deletePage(int id) {
        departmentController.deleteDepartment(Department.builder().id(id).build());
    }
}

