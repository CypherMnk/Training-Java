package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.dao.UserDao;
import com.training.model.User;
import com.training.util.DBConnection;

public class UserDaoImpl implements UserDao {

    @Override
    public void createTable() throws ClassNotFoundException, SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "user_id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) NOT NULL, "
                + "role VARCHAR(50) NOT NULL, "
                + "username VARCHAR(50) NOT NULL UNIQUE, "
                + "password VARCHAR(255) NOT NULL, "
                + "email VARCHAR(100) NOT NULL UNIQUE, "
                + "mobile VARCHAR(20) NOT NULL)";

        try (Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    @Override
    public List<User> findAll() throws ClassNotFoundException, SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, name, role, username, password, email, mobile FROM users";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(toUser(resultSet));
            }
        }
        return users;
    }

    @Override
    public User findById(int id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT user_id, name, role, username, password, email, mobile "
                + "FROM users WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? toUser(resultSet) : null;
            }
        }
    }

    @Override
    public int save(User user) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO users (name, role, username, password, email, mobile) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            setUserValues(statement, user);
            return statement.executeUpdate();
        }
    }

    @Override
    public int update(User user) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE users SET name = ?, role = ?, username = ?, password = ?, "
                + "email = ?, mobile = ? WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            setUserValues(statement, user);
            statement.setInt(7, user.getUserId());
            return statement.executeUpdate();
        }
    }

    @Override
    public int delete(User user) throws ClassNotFoundException, SQLException {
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM users WHERE user_id = ?")) {
            statement.setInt(1, user.getUserId());
            return statement.executeUpdate();
        }
    }

    @Override
    public User isValidUser(String username, String password)
            throws ClassNotFoundException, SQLException {
        String sql = "SELECT user_id, name, role, username, password, email, mobile "
                + "FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? toUser(resultSet) : null;
            }
        }
    }

    private void setUserValues(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getRole());
        statement.setString(3, user.getUsername());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getMobile());
    }

    private User toUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("user_id"), resultSet.getString("name"),
                resultSet.getString("role"), resultSet.getString("username"),
                resultSet.getString("password"), resultSet.getString("email"),
                resultSet.getString("mobile"));
    }
}
