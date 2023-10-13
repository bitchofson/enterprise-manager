package ru.vsu.remezov.app.console;

import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;
import ru.vsu.remezov.presentation.view.console.ConsoleView;


public class Main {
    public static void main(String[] args) {

        var departmentController = new DepartmentController();
        var employeeController = new EmployeeController();

        var console = new ConsoleView(employeeController, departmentController);
        console.view();

    }
}
