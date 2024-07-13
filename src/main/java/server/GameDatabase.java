package server;

import model.User;

import java.sql.*;
import java.util.ArrayList;

public class GameDatabase {
    private static final String URL = "jdbc:sqlite:gwent_game.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "username TEXT NOT NULL, " + "password TEXT NOT NULL, " + "nickname TEXT NOT NULL, " + "email TEXT NOT NULL, " + "answer TEXT, " + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(createUsersTable);
            System.out.println("Table 'users' created or already exists.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, nickname, email, answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNickname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getQuestion().getAnswer());
            preparedStatement.executeUpdate();
            System.out.println("User " + user.getUsername() + " created.");
        }
    }

    public static User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("nickname"), resultSet.getString("email"));
            }
        }
        return null;
    }

    public static User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("nickname"), resultSet.getString("email"));
            }
        }
        return null;
    }

    public static void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET password = ?, nickname = ?, email = ? WHERE username = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.executeUpdate();
            System.out.println("User " + user.getUsername() + " updated.");
        }
    }

    public static void deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.executeUpdate();
            System.out.println("User " + user.getUsername() + " deleted.");
        }
    }

    public static ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("nickname"), resultSet.getString("email")));
            }
        }
        return users;
    }


}
