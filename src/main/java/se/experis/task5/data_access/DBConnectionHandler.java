package se.experis.task5.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import se.experis.task5.logging.Logger;

public class DBConnectionHandler {

    public static Connection getConnection() {
        try {
            String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
            return DriverManager.getConnection(URL);
        }
        catch (SQLException error) {
            new Logger().error(error.getMessage());
            return null;
        }
    }
}
