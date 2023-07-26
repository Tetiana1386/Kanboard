package db.controllers;

import db.models.UserDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.EnvProperties.*;

public class UserDBController {
    private final static String INSERT_USER_SQL = "INSERT INTO users (username, password) VALUES(?, ?)";
    private final static String SELECT_ALL_USERS_SQL = "SELECT * FROM users";
    private final static String SELECT_USER_BY_ID_SQL = "SELECT * FROM users WHERE id=?";
    private final static String SELECT_USER_BY_NAME_SQL = "SELECT * FROM users WHERE username=?";
    private final static String UPDATE_USER_SQL = "UPDATE users SET role=? WHERE id=?";
    private final static String DELETE_USER_SQL = "DELETE FROM users WHERE id=?";
    private final static String EXCEPTION_MSG = String.format("Please check connection string URL [%s], name [%s], pass [%s]", DB_URL, DB_USER_NAME, DB_PASSWORD);

    public static void insertUserDB(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
    }

    public static UserDB getUserByIdDB(int id) {

        UserDB user = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new UserDB(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
        return user;
    }

    public static UserDB getUserByNameDB(String username) {

        UserDB user = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_NAME_SQL);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new UserDB(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
        return user;
    }

    public static List<UserDB> getAllUsersDB() {

        List<UserDB> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserDB user = new UserDB(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
        return users;
    }

    public static void updateUserDB(int id, String role) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);
            statement.setString(1, role);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
    }

    public static void deleteUserDB(Integer id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
    }
}
