package ru.vsu.remezov.domain;

public record Employee(int id, String fullName, int age, int salary, Department department) {

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder {

        private int id;
        private String fullName;
        private int age;
        private int salary;
        private Department department;

        public EmployeeBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public EmployeeBuilder age(int age) {
            this.age = age;
            return this;
        }

        public EmployeeBuilder salary(int salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeBuilder id(int id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder department(Department department) {
            this.department = department;
            return this;
        }

        public Employee build() {
            return new Employee(id, fullName, age, salary, department);
        }
    }

    @Override
    public String toString() {
        return "id: " + this.id + " ФИО: " + this.fullName + " "
                + " Возраст: " + this.age + " "
                + " Заработная плата: " + this.salary + " "
                + "Департамент: " + this.department.name();
    }
}
