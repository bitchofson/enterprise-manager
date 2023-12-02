package ru.vsu.remezov.infrastructure.repository.impl;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.domain.Employee;
import ru.vsu.remezov.infrastructure.repository.IRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            statement.setString(1, object.fullName());
            statement.setInt(2, object.age());
            statement.setInt(3, object.salary());
            statement.setInt(4, object.department().id());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return object;
    }

    @Override
    public Employee delete(Employee object) {

        String sql = "DELETE FROM employee WHERE id=?;";

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            statement.setInt(1, object.id());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return object;
    }

    @Override
    public Employee update(Employee object) {

        String sql = "UPDATE employee SET full_name=?, age=?, salary=?, department_id=? WHERE id=?;";

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            statement.setString(1, object.fullName());
            statement.setInt(2, object.age());
            statement.setInt(3, object.salary());
            statement.setInt(4, object.department().id());
            statement.setInt(5, object.id());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return object;
    }

    @Override
    public boolean isExist(int id) {

        boolean flag = false;
        String sql = String.format("SELECT employee.id FROM employee WHERE id=%d;", id);

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) flag = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Employee> findAll() {

        List<Employee> result = new ArrayList<>();
        String sql =
                """
                SELECT DISTINCT
                employee.id,
                employee.full_name,
                employee.age,
                employee.salary,
                department.id AS department_id,
                COALESCE(department.name, 'Без отдела') AS department_name
                FROM employee
                LEFT JOIN department ON employee.department_id = department.id;
                """;

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    result.add(Employee.builder()
                            .id(resultSet.getInt("id"))
                            .fullName(resultSet.getString("full_name"))
                            .age(resultSet.getInt("age"))
                            .salary(resultSet.getInt("salary"))
                            .department(Department.builder().id(resultSet.getInt("department_id")).name(resultSet.getString("department_name")).build())
                            .build());
            }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public Employee findById(int id) {
        Employee result = null;
        String sql =
                """
                SELECT DISTINCT
                employee.id,
                employee.full_name,
                employee.age,
                employee.salary,
                department.id AS department_id,
                COALESCE(department.name, 'Без отдела') AS department_name
                FROM employee
                LEFT JOIN department ON employee.department_id = department.id;
                """;

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    result = Employee.builder()
                            .id(resultSet.getInt("id"))
                            .fullName(resultSet.getString("full_name"))
                            .age(resultSet.getInt("age"))
                            .salary(resultSet.getInt("salary"))
                            .department(Department.builder().id(resultSet.getInt("department_id")).name(resultSet.getString("department_name")).build())
                            .build();
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

	@Override
	public Employee findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
