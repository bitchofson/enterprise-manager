package ru.vsu.remezov.presentation.controller;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.implementation.InMemoryDepartmentRepository;
import ru.vsu.remezov.usecase.implementation.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class DepartmentController {
    private final DepartmentService departmentService = new DepartmentService(
            new InMemoryDepartmentRepository(),
            () -> UUID.randomUUID().toString()
    );

    public void createDepartment(String name, List<Employee> employees) {
        departmentService.create(Department.builder().name(name).employees(employees).build());
    }
    public Map<String, Integer> getAllSalaryDepartments() {
        var departments = departmentService.findAll();
        return departments.stream()
                .collect(Collectors.toMap(
                        Department::name,
                        department -> department.employees().stream()
                                .mapToInt(Employee::salary)
                                .sum()
                ));
    }

    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }
}
