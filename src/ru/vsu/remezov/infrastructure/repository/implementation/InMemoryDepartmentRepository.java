package ru.vsu.remezov.infrastructure.repository.implementation;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.infrastructure.repository.Repository;

import java.util.*;

public class InMemoryDepartmentRepository implements Repository<Department> {

    private final Map<String, Department> memoryDb = new HashMap<>();
    @Override
    public Department save(Department object) {
        System.out.println("Отдел с id: "+ object.id() +" успешно сохранен в базе данных.");
        return memoryDb.put(object.id(), object);
    }

    @Override
    public Department delete(String id) {
        return memoryDb.remove(id);
    }

    @Override
    public Optional<Department> findById(String id) {
        return Optional.ofNullable(memoryDb.get(id));
    }

    @Override
    public List<Department> findAll() {
        return new ArrayList<>(memoryDb.values());
    }
}