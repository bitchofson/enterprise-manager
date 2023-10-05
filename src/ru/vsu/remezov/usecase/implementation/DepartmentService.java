package ru.vsu.remezov.usecase.implementation;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.infrastructure.repository.IRepository;
import ru.vsu.remezov.usecase.IdGenerator;

import java.util.List;
import java.util.Optional;

public class DepartmentService {

    IRepository<Department> repository;
    IdGenerator idGenerator;

    public DepartmentService(IRepository<Department> repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    public Department create(Department department) {
        Department departmentToSave = Department.builder()
                .id(idGenerator.generate())
                .name(department.name())
                .employees(department.employees())
                .build();
        return repository.create(departmentToSave);
    }

    public Department delete(String id) {
        return repository.delete(id);
    }

    public Optional<Department> findById(String id) {
        return repository.findById(id);
    }

    public List<Department> findAll() {
        return repository.findAll();
    }

}
