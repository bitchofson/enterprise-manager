package ru.vsu.remezov.usecase.impl;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.IRepository;
import ru.vsu.remezov.usecase.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DepartmentService implements IService<Department> {

    IRepository<Department> IRepository;

    public DepartmentService(IRepository<Department> IRepository) {
        this.IRepository = IRepository;
    }

    @Override
    public void create(Department department) {
        IRepository.create(department);
    }

    @Override
    public void update(Department department) {
        IRepository.update(department);
    }

    @Override
    public void delete(Department department) {
        IRepository.delete(department);
    }

    @Override
    public boolean isExist(int id) {
        return IRepository.isExist(id);
    }

    @Override
    public Department findById(int id) {
        return IRepository.findById(id);
    }

    @Override
    public List<Department> findAll() {
        return IRepository.findAll();
    }
    
    @Override
    public Department findByName(String name) {
        return IRepository.findByName(name);
    }
    
    public Map<String, Integer> getDepartmentSalarySums(List<Employee> employees) {
        HashMap<String, Integer> departmentSalarySums = new HashMap<>();
        for (Employee employee: employees) {
            if (employee.department().name() != null) {
            	if (!employee.department().name().equals("Без отдела")) {
                String departmentName = employee.department().name();
                int salary = employee.salary();
                if (!departmentSalarySums.containsKey(departmentName)) {
                    departmentSalarySums.put(departmentName, salary);
                } else {
                    departmentSalarySums.put(departmentName, departmentSalarySums.get(departmentName) + salary);
                }
            }
            }
        }

        return departmentSalarySums;
    }

}
