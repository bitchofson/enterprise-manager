package ru.vsu.remezov.infrastructure.repository.implementation;

import ru.vsu.remezov.domain.Department;
import ru.vsu.remezov.infrastructure.repository.IRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        try {

            PreparedStatement statement = this.connection.getConnection().prepareStatement(sql);
            statement.setString(1, object.name());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return object;
    }

    @Override
    public Department delete(Department object) {
        String sql = "DELETE FROM department WHERE id=?;";
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
    public Department update(Department objectOld,
                             Department objectNew) {

        String sql = "UPDATE department SET name=? WHERE id=?;";

        try {

            PreparedStatement statement = this.connection.getConnection().prepareStatement(sql);
            statement.setString(1, objectNew.name());
            statement.setInt(2, objectOld.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return objectOld;
    }
    @Override
    public boolean findById(int id) {
            Department department = Department.builder().name("NULL").build();;
        try {
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM DEPARTMENT WHERE id=%s;", id));
            if (resultSet.next()) {
                department = Department.builder().name(resultSet.getString("name")).build();
            }
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return !department.name().equals("NULL");
    }
    @Override
    public List<Department> findAll() {
        List<Department> result = new ArrayList<>();

        try {
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENT;");
            while (resultSet.next()) {
                result.add(Department.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build());
            }
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }
}
