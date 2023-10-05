package ru.vsu.remezov.infrastructure.repository.implementation;

import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.IRepository;

import java.util.*;

public class InMemoryEmployeeRepository implements IRepository<Employee> {

    private final Map<String, Employee> memoryDb = new HashMap<>();

    @Override
    public Employee create(Employee employee) {
        memoryDb.put(employee.id(), employee);
        System.out.println("Сотрудник с id: "+ employee.id() +" успешно создан и добавлен в базу данных.");
        return employee;
    }

    @Override
    public Employee delete(String id) {
        return memoryDb.remove(id);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(memoryDb.get(id));
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(memoryDb.values());
    }
}
