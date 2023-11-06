package ru.vsu.remezov.presentation.view.console;

import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;
import ru.vsu.remezov.presentation.view.View;

import java.util.Scanner;

public class ConsoleView implements View {

    EmployeeController employeeController;
    DepartmentController departmentController;
    Scanner input;
    MenuManager mainMenu, departmentCreatorMenu, employeeCreatorMenu, departmentEditorMenu, employeeEditorMenu;

    public ConsoleView(EmployeeController employeeController, DepartmentController departmentController) {

        this.employeeController = employeeController;
        this.departmentController = departmentController;

        this.input = new Scanner(System.in);

        departmentCreatorMenu = new DepartmentCreatorMenuManager(departmentController, employeeController, input);
        employeeCreatorMenu = new EmployeeCreatorMenuManager(employeeController, input);

        departmentEditorMenu = new DepartmentEditorMenuManager(departmentController, employeeController, input);
        employeeEditorMenu = new EmployeeEditorMenuManager(employeeController, departmentController, input);

        mainMenu = new MainMenuManager(
                departmentController, employeeController, input,
                departmentCreatorMenu, employeeCreatorMenu,
                departmentEditorMenu, employeeEditorMenu
        );
    }

    @Override
    public void view() {
        mainMenu.show();
    }

}
