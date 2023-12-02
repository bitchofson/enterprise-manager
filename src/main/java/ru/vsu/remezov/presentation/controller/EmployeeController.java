package ru.vsu.remezov.presentation.controller;

import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.usecase.impl.EmployeeService;
import java.util.List;



public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void createEmployee(Employee employee) {
        employeeService.create(employee);
    }

    public void updateEmployee(Employee employee) { employeeService.update(employee); }

    public void deleteEmployee(Employee employee) {
        employeeService.delete(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeService.findAll();
    }

    public boolean isExist(int id) {
        return employeeService.isExist(id);
    }

    public Employee findById(int id) { return employeeService.findById(id); }


}
