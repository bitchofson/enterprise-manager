package ru.vsu.remezov.infrastructure.repository.implementation;

import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.Repository;

import java.util.*;

public class InMemoryEmployeeRepository implements Repository<Employee> {

    private final Map<String, Employee> memoryDb = new HashMap<>();

    @Override
    public Employee save(Employee employee) {
        memoryDb.put(employee.id(), employee);
        System.out.println("Сотрудник с id: "+ employee.id() +" успешно сохранен в базе данных.");
        return memoryDb.get(employee.id());
    }

    @Override
    public Employee delete(String id) {
        System.out.println("Сотрудник с id: "+ id +" успешно удален из базы данных.");
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
