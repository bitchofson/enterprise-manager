package ru.vsu.remezov.presentation.view.console;

import ru.vsu.remezov.presentation.controller.EmployeeController;


import java.util.Scanner;

public class EmployeeCreatorMenuManager implements MenuManager {

    private final EmployeeController employeeController;
    private final Scanner input;

    public EmployeeCreatorMenuManager(EmployeeController employeeController, Scanner input) {
        this.employeeController = employeeController;
        this.input = input;
    }

    @Override
    public void show() {
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
}
