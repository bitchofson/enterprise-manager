package ru.vsu.remezov.usecase.implementation;

import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.Repository;
import ru.vsu.remezov.usecase.IdGenerator;

import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final Repository<Employee> repository;
    private final IdGenerator idGenerator;

    public EmployeeService(Repository<Employee> repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    public void create(String fullName, String age, int salary) {
        Employee employeeToSave = Employee.builder()
                .id(idGenerator.generate())
                .fullName(fullName)
                .age(age)
                .salary(salary)
                .build();
        repository.save(employeeToSave);
    }

    public Employee edit(String id, String fullName, String age, int salary) {
        Employee employeeToSave = Employee.builder()
                .id(id)
                .fullName(fullName)
                .age(age)
                .salary(salary)
                .build();
        return repository.save(employeeToSave);
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
