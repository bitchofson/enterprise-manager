package ru.vsu.remezov.domain;

public record Employee(String id, String fullName, String age, int salary) {

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder {

        private String id;
        private String fullName;
        private String age;
        private int salary;

        public EmployeeBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public EmployeeBuilder age(String age) {
            this.age = age;
            return this;
        }

        public EmployeeBuilder salary(int salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeBuilder id(String id) {
            this.id = id;
            return this;
        }


        public Employee build() {
            return new Employee(id, fullName, age, salary);
        }
    }

    @Override
    public String toString() {
        return "id: " + this.id + " ФИО: " + this.fullName + " "
                + " Возраст: " + this.age + " "
                + " Заработная плата: " + this.salary;
    }
}
