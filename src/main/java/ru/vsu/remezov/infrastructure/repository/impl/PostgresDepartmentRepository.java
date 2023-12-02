package ru.vsu.remezov.infrastructure.repository.impl;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.infrastructure.repository.IRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresDepartmentRepository implements IRepository<Department> {

    PostgresStorage connection;
    public PostgresDepartmentRepository(PostgresStorage connection) {
        this.connection = connection;
    }

    @Override
    public Department create(Department object) {

        String sql = "INSERT INTO department (name) VALUES (?);";

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            statement.setString(1, object.name());
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Операция отменена.");
            System.out.println("Такое имя департамента уже существует!");        }

        return object;
    }

    @Override
    public Department delete(Department object) {

        String sql = "DELETE FROM department WHERE id=?;";

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            statement.setInt(1, object.id());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return object;
    }

    @Override
    public Department update(Department object) {

        String sql = "UPDATE department SET name=? WHERE id=?;";

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            statement.setString(1, object.name());
            statement.setInt(2, object.id());
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Операция отменена.");
            System.err.println("Такое имя департамента уже существует!");
        }

        return object;
    }
    @Override
    public boolean isExist(int id) {

        boolean flag = false;
        String sql = String.format("SELECT department.id FROM department WHERE id=%d;", id);

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) flag = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return flag;
    }
    @Override
    public List<Department> findAll() {

        List<Department> result = new ArrayList<>();
        String sql = "SELECT * FROM department;";

        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    result.add(Department.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .build());
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public Department findById(int id) {
        Department result = null;
        String sql = String.format("SELECT * FROM department WHERE id=%d;", id);


        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = Department.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .build();
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }
    
    @Override
    public Department findByName(String name) {
        Department result = null;
        String sql = String.format("SELECT * FROM department WHERE department.name='%s';", name);


        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(sql)){
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = Department.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .build();
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
