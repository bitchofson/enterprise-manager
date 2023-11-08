package ru.vsu.remezov.presentation.controller;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.usecase.implementation.DepartmentService;

import java.util.HashMap;
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

    public void updateDepartment(Department departmentOld,
                                  Department departmentNew) {
        departmentService.update(departmentOld, departmentNew);
    }

    public void deleteDepartment(Department department) {
        departmentService.delete(department);
    }

    public boolean isExist(int id) {
        return departmentService.isExist(id);
    }

    public Map<String, Integer> getDepartmentSalarySums(List<Employee> employees) {
        HashMap<String, Integer> departmentSalarySums = new HashMap<>();
        for (Employee employee: employees) {
            if (employee.department().name() != null) {
                String departmentName = employee.department().name();
                int salary = employee.salary();
                if (!departmentSalarySums.containsKey(departmentName)) {
                    departmentSalarySums.put(departmentName, salary);
                } else {
                    departmentSalarySums.put(departmentName, departmentSalarySums.get(departmentName) + salary);
                }
            }
        }

        return departmentSalarySums;
    }
}
