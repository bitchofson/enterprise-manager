package ru.vsu.remezov.presentation.controller;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.usecase.impl.DepartmentService;

import java.util.List;
import java.util.Map;

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void createDepartment(Department department) {
        departmentService.create(department);
    }

    public List<Department> findAllDepartments() {
        return departmentService.findAll();
    }

    public void updateDepartment(Department department) {
        departmentService.update(department);
    }

    public void deleteDepartment(Department department) {
        departmentService.delete(department);
    }

    public boolean isExist(int id) {
        return departmentService.isExist(id);
    }

    public Department findById(int id) { return departmentService.findById(id); }
    
    public Department findByName(String name) { return departmentService.findByName(name); }


    public Map<String, Integer> getDepartmentSalarySums(List<Employee> employees) {
        return departmentService.getDepartmentSalarySums(employees);
    }
}
