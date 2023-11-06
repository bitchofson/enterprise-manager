package ru.vsu.remezov.infrastructure.repository.implementation;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.IRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PostgresEmployeeRepository implements IRepository<Employee> {

    PostgresStorage connection;

    public PostgresEmployeeRepository(PostgresStorage connection) {
        this.connection = connection;
    }

    @Override
    public Employee create(Employee object) {
        String sql = "INSERT INTO employee (full_name, age, salary, department_id) VALUES (?, ?, ?, ?);";
        try {

            PreparedStatement statement = this.connection.getConnection().prepareStatement(sql);
            statement.setString(1, object.fullName());
            statement.setInt(2, object.age());
            statement.setInt(3, object.salary());
            statement.setInt(4, object.department().id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return object;
    }

    @Override
    public Employee delete(Employee object) {
        String sql = "DELETE FROM employee WHERE id=?;";
        try {

            PreparedStatement statement = this.connection.getConnection().prepareStatement(sql);
            statement.setInt(1, object.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return object;
    }

    @Override
    public Employee update(Employee objectOld,
                           Employee objectNew) {

        String sql = "UPDATE employee SET full_name=?, age=?, salary=?, department_id=? WHERE id=?;";

        try {

            PreparedStatement statement = this.connection.getConnection().prepareStatement(sql);
            statement.setString(1, objectNew.fullName());
            statement.setInt(2, objectNew.age());
            statement.setInt(3, objectNew.salary());
            statement.setInt(4, objectNew.department().id());
            statement.setInt(5, objectOld.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return objectOld;
    }

    @Override
    public boolean findById(int id) {
        Employee employee = Employee.builder().id(-1).build();
        try {
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM employee WHERE id=%s;", id));
            if (resultSet.next()) {
                employee = Employee.builder().id(resultSet.getInt("id")).build();
            }
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return employee.id() != -1;
    }

    @Override
    public List<Employee> findAll() {

        List<Employee> resultWithOutName = findWithOutName();
        List<Employee> resultWithName = new ArrayList<>();

        try {
            Statement statement = this.connection.getConnection().createStatement();
            for (Employee employee : resultWithOutName) {
                ResultSet resultSet = statement.executeQuery(String.format("SELECT name from department WHERE department.id=%d", employee.department().id()));
                if (resultSet.next()) {
                    resultWithName.add(Employee.builder()
                            .id(employee.id())
                            .fullName(employee.fullName())
                            .age(employee.age())
                            .salary(employee.salary())
                            .department(Department.builder().id(employee.department().id()).name(resultSet.getString(1)).build())
                            .build());
                } else {
                    resultWithName.add(Employee.builder()
                            .id(employee.id())
                            .fullName(employee.fullName())
                            .age(employee.age())
                            .salary(employee.salary())
                            .department(Department.builder().id(employee.department().id()).build())
                            .build());
                }
            }

            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return resultWithName;
    }

    public List<Employee> findWithOutName() {
        List<Employee> result = new ArrayList<>();

        try {
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee;");

            while (resultSet.next()) {
                result.add(Employee.builder()
                        .id(resultSet.getInt("id"))
                        .fullName(resultSet.getString("full_name"))
                        .age(resultSet.getInt("age"))
                        .salary(resultSet.getInt("salary"))
                        .department(Department.builder().id(resultSet.getInt("department_id")).build())
                        .build());
            }
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }
}
