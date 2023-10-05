package ru.vsu.remezov.domain;
import java.util.List;

public record Department(String id, String name, List<Employee> employees) {

    public static DepartmentBuilder builder() {
        return new DepartmentBuilder();
    }

    public static class DepartmentBuilder {
        private String id;
        private String name;
        private List<Employee> employees;

        public DepartmentBuilder id(String id) {
            this.id = id;
            return this;
        }

        public DepartmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DepartmentBuilder employees(List<Employee> employees) {
            this.employees = employees;
            return this;
        }

        public Department build() {
            return new Department(id, name, employees);
        }
    }

    @Override
    public String toString() {
        return "\nid: " + this.id + " name: " + this.name + " " + employees.stream().toList();
    }
}