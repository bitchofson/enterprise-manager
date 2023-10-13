package ru.vsu.remezov.presentation.controller;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.implementation.InMemoryDepartmentRepository;
import ru.vsu.remezov.usecase.implementation.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class DepartmentController {
    private final DepartmentService departmentService = new DepartmentService(
            new InMemoryDepartmentRepository(),
            () -> UUID.randomUUID().toString()
    );

    public void createDepartment(String name, List<Employee> employees) {
        departmentService.create(Department.builder().name(name).employees(employees).build());
    }
    public Map<String, Integer> getAllSalaryDepartments() {
        return departmentService.getAllSalaryDepartments();
    }
    public boolean isPresent(String id) {
        Optional<Department> department = departmentService.findById(id);
        return department.isPresent();
    }
    public List<Department> findAllDepartments() {
        return departmentService.findAll();
    }

    public void updateDepartments(Employee employee) {
        List<Department> departments = findAllDepartments();
        for (Department department: departments) {
            departmentService.update(department, employee);
        }
    }

    public void addEmployeesToDepartment(String id, List<Employee> employees) {
        Optional<Department> department = departmentService.findById(id);
        departmentService.addEmployees(department.get(), employees);

    }

    public void deleteEmployeesFromDepartment(String id, List<Employee> employees) {
        Optional<Department> department = departmentService.findById(id);
        departmentService.deleteEmployees(department.get(), employees);
    }
}
