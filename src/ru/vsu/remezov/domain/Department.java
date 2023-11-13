package ru.vsu.remezov.domain;

public record Department(int id, String name) {

    public static DepartmentBuilder builder() {
        return new DepartmentBuilder();
    }

    public static class DepartmentBuilder {

        private int id;
        private String name;

        public DepartmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DepartmentBuilder id(int id) {
            this.id = id;
            return this;
        }

        public Department build() {
            return new Department(id, name);
        }
    }

    @Override
    public String toString() {
        return "id: " + this.id + " Название департамента: " + this.name;
    }
}