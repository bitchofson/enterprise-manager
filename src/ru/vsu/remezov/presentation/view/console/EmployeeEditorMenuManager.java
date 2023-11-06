package ru.vsu.remezov.presentation.view.console;


import ru.vsu.remezov.domain.Department;
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
            int id = input.nextInt();
            input.nextLine();
            if (employeeController.isPresent(id)) {
                editorPage(id);
            } else {
                System.out.println("Такого id нет!");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void editorPage(int id) {
        try {
            System.out.println("Отредактировать сотрудника (1)");
            System.out.println("Удалить сотрудника (2)");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> updatePage(id);
                case 2 -> deletePage(id);
                case 3 -> System.out.println("Введено неверное число!");
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void updatePage(int id) {
        try {
            System.out.println("\nВведите новое имя сотрудника:");
            String name = input.nextLine();
            System.out.println("Введите заработную плату:");
            int salary = input.nextInt();
            System.out.println("Введите возраст: ");
            int age = input.nextInt();
            input.nextLine();
            System.out.println("Выбирете id нового департамента: ");
            departmentController.findAllDepartments().forEach(System.out::println);
            int idDepartment = input.nextInt();

            employeeController.updateEmployee(
                    Employee.builder().id(id).build(),
                    Employee.builder().fullName(name).age(age).salary(salary).department(Department.builder().id(idDepartment).build()).build()
            );

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void deletePage(int id) {
        employeeController.deleteEmployee(Employee.builder().id(id).build());
    }
}
