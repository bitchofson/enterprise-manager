package ru.vsu.remezov.presentation.view.console;


import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;


import java.util.*;

public class EmployeeEditorMenuManager implements MenuManager {

    private final EmployeeController employeeController;

    private final DepartmentController departmentController;
    private final Scanner input;

    public EmployeeEditorMenuManager(EmployeeController employeeController, DepartmentController departmentController, Scanner input) {
        this.employeeController = employeeController;
        this.departmentController = departmentController;
        this.input = input;
    }


    @Override
    public void show() {
        try {
            System.out.println("Выберите из представленного списка id сотрудника, которого хотите изменить: ");
            employeeController.findAllEmployees().forEach(System.out::println);
            String id = input.nextLine();
            if (employeeController.isPresent(id)) {
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

            System.out.print("Введите ФИО: ");
            String fullName = input.nextLine();
            System.out.print("Введите возраст: ");
            String age = input.next();
            System.out.print("Введите заработную плату: ");
            int salary = input.nextInt();
            Employee employee = employeeController.editEmployee(id, fullName, age, salary);
            departmentController.updateDepartments(employee);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
