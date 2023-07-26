package db.controllers;

import db.models.ProjectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.EnvProperties.*;

public class ProjectDBController {
    private final static String INSERT_PROJECT_SQL = "INSERT INTO projects (name, owner_id) VALUES(?, ?)";
    private final static String SELECT_ALL_PROJECTS_SQL = "SELECT * FROM projects";
    private final static String SELECT_PROJECT_BY_ID_SQL = "SELECT * FROM projects WHERE id=?";
    private final static String SELECT_PROJECT_BY_NAME_SQL = "SELECT * FROM projects WHERE name=?";
    private final static String DELETE_PROJECT_SQL = "DELETE FROM projects WHERE id=?";
    private final static String EXCEPTION_MSG = String.format("Please check connection string URL [%s], name [%s], pass [%s]", DB_URL, DB_USER_NAME, DB_PASSWORD);

    public static void insertProjectDB(String name, Integer ownerId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(INSERT_PROJECT_SQL);
            statement.setString(1, name);
            statement.setInt(2, ownerId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
    }

    public static ProjectDB getProjectByIdDB(int id) {
        ProjectDB project = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_PROJECT_BY_ID_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                project = new ProjectDB(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("owner_id"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
        return project;
    }

    public static ProjectDB getProjectByNameDB(String name) {
        ProjectDB project = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_PROJECT_BY_NAME_SQL);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                project = new ProjectDB(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("owner_id"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
        return project;
    }

    public static List<ProjectDB> getAllProjectsDB() {
        List<ProjectDB> projects = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PROJECTS_SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProjectDB project = new ProjectDB(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("owner_id"));
                projects.add(project);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
        return projects;
    }

    public static void deleteProjectDB(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(DELETE_PROJECT_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
    }
}
