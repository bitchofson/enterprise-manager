package ru.vsu.remezov.usecase.implementation;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.Repository;
import ru.vsu.remezov.usecase.IdGenerator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepartmentService {

    Repository<Department> repository;
    IdGenerator idGenerator;

    public DepartmentService(Repository<Department> repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    public void create(Department department) {
        Department departmentToSave = Department.builder()
                .id(idGenerator.generate())
                .name(department.name())
                .employees(department.employees())
                .build();
        repository.save(departmentToSave);
    }

    public void update(Department department, Employee employee) {

        List <Employee> employees = department.employees();
        employees.removeIf(e -> e.id().equals(employee.id()));
        employees.add(employee);

        Department departmentToSave = Department.builder()
                .id(department.id())
                .name(department.name())
                .employees(employees)
                .build();
        repository.save(departmentToSave);
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
    public Map<String, Integer> getAllSalaryDepartments() {
        var departments = findAll();
        return departments.stream()
                .collect(Collectors.toMap(
                        Department::name,
                        department -> department.employees().stream()
                                .mapToInt(Employee::salary)
                                .sum()
                ));
    }

    public void deleteEmployees(Department department, List<Employee> employees) {
        List <Employee> employeesDepartment = department.employees();

        for (Employee employee: employees) {
            employeesDepartment.removeIf(e -> e.id().equals(employee.id()));
        }

        Department departmentToSave = Department.builder()
                .id(department.id())
                .name(department.name())
                .employees(employeesDepartment)
                .build();
        repository.save(departmentToSave);
    }

    public void addEmployees(Department department, List<Employee> employees) {
        List <Employee> employeesDepartment = department.employees();

        for (Employee employee: employees) {
            employeesDepartment.removeIf(e -> e.id().equals(employee.id()));
            employeesDepartment.add(employee);
        }

        Department departmentToSave = Department.builder()
                .id(department.id())
                .name(department.name())
                .employees(employeesDepartment)
                .build();
        repository.save(departmentToSave);
    }
}
