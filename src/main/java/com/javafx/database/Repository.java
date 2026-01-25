package com.javafx.database;

import com.javafx.entity.exception.DatabaseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public interface Repository<T> {

    String DATABASE_FILE = "src/main/resources/database.properties";

    default Connection createConnection() throws DatabaseException, IOException {
        try (var reader = new FileReader(DATABASE_FILE)) {

            var properties = new Properties();
            properties.load(reader);

            var url = properties.getProperty("url");
            var username = properties.getProperty("username");
            var password = properties.getProperty("password");

            return DriverManager.getConnection(url, username, password);

        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    default void closeConnection(Connection connection) throws DatabaseException {

        try {
            connection.close();
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    List<T> getAll() throws DatabaseException, IOException;
    Optional<T> getOneById(Integer id) throws DatabaseException, IOException;
}
