package ru.vsu.remezov.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PostgresStorage extends DatabaseStorage {


    private static final int DEFAULT_DB_PORT = 5432;
    private static final String DEFAULT_DB_NAME = "enterprise_manager_db";
    private static final String DEFAULT_USERNAME = "xzqtbl";
    private static final String DEFAULT_PASSWORD = "SanyaXyuCocu";
    private static final String DEFAULT_ADRESS = "localhost";

    private final int    dbPort;
    private final String dbName;
    private final String dbUsername;
    private final String dbPassword;
    private final String dbAdress;

    private Connection conn = null;

    public PostgresStorage(
            int    dbPort,
            String dbName,
            String dbUsername,
            String dbPassword,
            String dbAdress
    ) {
        this.dbPort     = dbPort;
        this.dbName     = dbName;
        this.dbAdress   = dbAdress;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public PostgresStorage() {
        this(
                PostgresStorage.DEFAULT_DB_PORT,
                PostgresStorage.DEFAULT_DB_NAME,
                PostgresStorage.DEFAULT_USERNAME,
                PostgresStorage.DEFAULT_PASSWORD,
                PostgresStorage.DEFAULT_ADRESS
        );
    }

    @Override
    public void connect() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://" + this.dbAdress + ":" + this.dbPort + "/" + this.dbName;
        System.out.println("URL: " + url);
        this.conn = DriverManager.getConnection(url, this.dbUsername, this.dbPassword);

    }

    @Override
    public void disconnect() throws SQLException {
        this.conn.close();
    }

    public Connection getConnection() {
        return conn;
    }
}
