package com.gym.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

     // You only need to change the name of the database in the URL, unless PG runs on another port on your system. Default port is 5432 
    private static final String URL = "jdbc:postgresql://localhost:5432/DB1";

    private static final String USER = "postgres";
    private static final String PASSWORD = "keyin1234";

    public static Connection getConnection() throws SQLException, SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            DBConnection.getConnection();
            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
