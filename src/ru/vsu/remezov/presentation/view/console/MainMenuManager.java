package ru.vsu.remezov.presentation.view.console;

import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;

import java.util.Scanner;

public class MainMenuManager implements MenuManager {

    private final DepartmentController departmentController;
    private final EmployeeController employeeController;
    private Scanner input;
    MenuManager departmentCreatorMenu, employeeCreatorMenu, departmentEditorMenu, employeeEditorMenu;

    public MainMenuManager(DepartmentController departmentController, EmployeeController employeeController, Scanner input,
                           MenuManager departmentCreatorMenu, MenuManager employeeCreatorMenu,
                           MenuManager departmentEditorMenu, MenuManager employeeEditorMenu) {

        this.departmentController = departmentController;
        this.employeeController = employeeController;

        this.input = input;

        this.departmentCreatorMenu = departmentCreatorMenu;
        this.employeeCreatorMenu = employeeCreatorMenu;
        this.departmentEditorMenu = departmentEditorMenu;
        this.employeeEditorMenu = employeeEditorMenu;
    }


    @Override
    public void show() {
        userInput();
    }

    private void userInput() {
        boolean done = false;
        int choice;

        while (!done) {
            try {
                mainPage();
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
            case 1 -> employeeController.findAllEmployees().forEach(System.out::println);
            case 2 -> {
                departmentController.findAllDepartments().forEach(System.out::println);
                departmentController.getAllSalaryDepartments().forEach((key, value) -> System.out.println("Общая сумма зарплат для отдела " + key + " " + value));
            }
            case 3 -> employeeCreatorMenu.show();
            case 4 -> departmentCreatorMenu.show();
            case 5 -> employeeEditorMenu.show();
            case 6 -> departmentEditorMenu.show();
            case 7 -> { return true; }
            default -> System.out.println("Введите корректное число в диапозоне от 1 до 7!\n");
        }

        return false;
    }
    private void mainPage() {
        System.out.println("-----------------------------");
        System.out.println("\t\tГлавное Меню");
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
