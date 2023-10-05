package ru.vsu.remezov.infrastructure.repository.implementation;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.infrastructure.repository.IRepository;

import java.util.*;

public class InMemoryDepartmentRepository implements IRepository<Department> {

    private final Map<String, Department> memoryDb = new HashMap<>();

    @Override
    public Department create(Department object) {
        memoryDb.put(object.id(), object);
        System.out.println("Отдел с id: "+ object.id() +" успешно создан и добавлен в базу данных.");
        return object;
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