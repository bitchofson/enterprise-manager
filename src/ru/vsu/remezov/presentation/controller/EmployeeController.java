package ru.vsu.remezov.presentation.controller;

import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.implementation.InMemoryEmployeeRepository;
import ru.vsu.remezov.usecase.implementation.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class EmployeeController {
    private final EmployeeService employeeService = new EmployeeService(
            new InMemoryEmployeeRepository()
    );

    public void createEmployee(String fullName, String age, int salary) {
        employeeService.create(fullName, age, salary);
    }

    public Employee editEmployee(String id, String fullName, String age, int salary) {
        return employeeService.edit(id, fullName, age, salary);
    }

    public List<Employee> getEmployees(String[] ids) {
        List<Employee> employees = new ArrayList<>();
        for (String id : ids) {
            Optional<Employee> employee = employeeService.findById(id);
            employee.ifPresent(employees::add);
        }
        return employees;
    }

    public boolean isPresent(String id) {
        Optional<Employee> employee = employeeService.findById(id);
        return employee.isPresent();
    }

    public List<Employee> findAllEmployees() {
        return employeeService.findAll();
    }

}
