package ru.gb.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DbInitializer implements CommandLineRunner {

    private static final String URL = "jdbc:mysql://localhost:3307/";
    private static final String DB_NAME = "USERS";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        createTable();
        insertData();
    }

    private void createTable() {
        try (Connection con = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
             Statement stmt = con.createStatement()) {

            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(256)," +
                    "email VARCHAR(128)" +
                    ")";
            stmt.executeUpdate(createTableQuery);

        } catch (SQLException e) {
            System.err.println("Не удалось создать таблицу: " + e.getMessage());
        }
    }

    private void insertData() {
        User user1 = new User("Катя", "gmail.rugb123");
        User user2 = new User("Паша", "gmail.rugb231");
        User user3 = new User("Юра", "gmail.rugb556");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}