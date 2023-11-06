package ru.vsu.remezov.usecase.implementation;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.infrastructure.repository.IRepository;
import ru.vsu.remezov.usecase.IService;

import java.util.List;


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
    public void update(Department departmentOld,
                       Department departmentNew) {
        IRepository.update(departmentOld, departmentNew);
    }

    @Override
    public void delete(Department department) {
        IRepository.delete(department);
    }

    @Override
    public boolean findById(int id) {
        return IRepository.findById(id);
    }

    @Override
    public List<Department> findAll() {
        return IRepository.findAll();
    }

}
