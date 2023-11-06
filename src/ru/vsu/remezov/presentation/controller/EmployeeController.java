package ru.vsu.remezov.presentation.controller;

import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.usecase.implementation.EmployeeService;
import java.util.List;



public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void createEmployee(Employee employee) {
        employeeService.create(employee);
    }

    public void updateEmployee(Employee employeeOld,
                               Employee employeeNew) {

        employeeService.update(employeeOld, employeeNew);
    }

    public void deleteEmployee(Employee employee) {
        employeeService.delete(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeService.findAll();
    }

    public boolean isPresent(int id) {
        return employeeService.findById(id);
    }

}
