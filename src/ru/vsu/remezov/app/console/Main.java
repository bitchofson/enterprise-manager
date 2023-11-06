package ru.vsu.remezov.app.console;

import ru.vsu.remezov.infrastructure.repository.implementation.PostgresDepartmentRepository;
import ru.vsu.remezov.infrastructure.repository.implementation.PostgresEmployeeRepository;
import ru.vsu.remezov.infrastructure.repository.implementation.PostgresStorage;
import ru.vsu.remezov.presentation.controller.DepartmentController;
import ru.vsu.remezov.presentation.controller.EmployeeController;
import ru.vsu.remezov.presentation.view.console.ConsoleView;
import ru.vsu.remezov.usecase.implementation.DepartmentService;
import ru.vsu.remezov.usecase.implementation.EmployeeService;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {

        try {
            PostgresStorage postgresStorage = new PostgresStorage();
            postgresStorage.connect();

            var departmentController = new DepartmentController(new DepartmentService(new PostgresDepartmentRepository(postgresStorage)));
            var employeeController = new EmployeeController(new EmployeeService(new PostgresEmployeeRepository(postgresStorage)));

            var console = new ConsoleView(employeeController, departmentController);
            console.view();

            postgresStorage.disconnect();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
