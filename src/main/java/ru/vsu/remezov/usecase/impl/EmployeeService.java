package ru.vsu.remezov.usecase.impl;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.IRepository;
import ru.vsu.remezov.usecase.IService;

import java.util.List;

public class EmployeeService implements IService<Employee> {

    IRepository<Employee> IRepository;

    public EmployeeService(IRepository<Employee> IRepository) {
        this.IRepository = IRepository;
    }

    @Override
    public void create(Employee employee) {
        IRepository.create(employee);
    }

    @Override
    public void update(Employee employee) {
        IRepository.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        IRepository.delete(employee);
    }

    @Override
    public boolean isExist(int id) {
        return IRepository.isExist(id);
    }

    @Override
    public Employee findById(int id) {
        return IRepository.findById(id);
    }
    
    @Override
    public Employee findByName(String name) {
        return IRepository.findByName(name);
    }

    @Override
    public List<Employee> findAll() {
        return IRepository.findAll();
    }
}
