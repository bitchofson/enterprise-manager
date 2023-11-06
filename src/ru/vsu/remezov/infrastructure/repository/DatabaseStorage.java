package ru.vsu.remezov.infrastructure.repository;

import java.sql.SQLException;

public abstract class DatabaseStorage {
    public abstract void connect() throws SQLException;
    public abstract void disconnect() throws SQLException;
}
