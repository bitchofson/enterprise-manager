package ru.vsu.remezov.presentation.controller;

import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.implementation.InMemoryEmployeeRepository;
import ru.vsu.remezov.usecase.implementation.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeService(
            new InMemoryEmployeeRepository(),
            () -> UUID.randomUUID().toString()
    );

    public void createEmployee(String fullName, String age, int salary) {
        employeeService.create(Employee.builder().fullName(fullName).age(age).salary(salary).build());
    }

    public List<Employee> getEmployees(String[] ids) {
        List<Employee> employees = new ArrayList<>();
        for (String id : ids) {
            Optional<Employee> employee = employeeService.findById(id);
            employee.ifPresent(employees::add);
        }
        return employees;
    }
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

}
