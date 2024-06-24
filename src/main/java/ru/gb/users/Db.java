package ru.gb.users;

import java.sql.*;

public class Db {
    private static final String URL = "jdbc:mysql://localhost:3307/";
    private static final String DB_NAME = "USERS";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";


    public static void con() {
        try {
            Connection con = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            Statement stmt = con.createStatement();
            //stmt.execute("DROP SCHEMA STUDENTS");
            //stmt.execute("CREATE SCHEMA STUDENTS");

            // Создание таблицы студентов
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(256)," +
                    "email VARCHAR(128)" +
                    ")";
            stmt.executeUpdate(createTableQuery);

            // Наполнение таблицы данными
            String insertDataQuery = "INSERT INTO users (name, email) VALUES " +
                    "('Катя', 'gmail.rugb123')," +
                    "('Паша', 'gmail.rugb231')," +
                    "('Юра', 'gmail.rugb556'),";
            stmt.executeUpdate(insertDataQuery);

            con.close();

        } catch (SQLException e) {
            System.err.println("Не удалось подключиться к БД: " + e.getMessage());
        }
    }

}