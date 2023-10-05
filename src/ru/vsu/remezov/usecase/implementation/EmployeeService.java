package ru.vsu.remezov.usecase.implementation;

import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.IRepository;
import ru.vsu.remezov.usecase.IdGenerator;

import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final IRepository<Employee> repository;
    private final IdGenerator idGenerator;

    public EmployeeService(IRepository<Employee> repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    public Employee create(Employee employee) {
        Employee employeeToSave = Employee.builder()
                .id(idGenerator.generate())
                .fullName(employee.fullName())
                .age(employee.age())
                .salary(employee.salary())
                .build();
        return repository.create(employeeToSave);
    }

    public Employee delete(String id) {
        return repository.delete(id);
    }

    public Optional<Employee> findById(String id) {
        return repository.findById(id);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }
}
