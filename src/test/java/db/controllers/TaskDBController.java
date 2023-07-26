package db.controllers;

import db.models.TaskDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.EnvProperties.*;

public class TaskDBController {
    private final static String INSERT_TASK_SQL = "INSERT INTO tasks (title, project_id) VALUES(?,?)";
    private final static String SELECT_ALL_TASKS_SQL = "SELECT * FROM kanboard.tasks";
    private final static String SELECT_TASK_BY_ID_SQL = "SELECT * FROM kanboard.tasks WHERE id=?";
    private final static String SELECT_TASK_BY_TITLE_SQL = "SELECT * FROM tasks WHERE title=?";
    private final static String CLOSE_TASK_SQL = "UPDATE tasks SET is_active=? WHERE id=?";
    private final static String DELETE_TASK_SQL = "DELETE FROM tasks WHERE id=?";
    private final static String EXCEPTION_MSG = String.format("Please check connection string URL [%s], name [%s], pass [%s]", DB_URL, DB_USER_NAME, DB_PASSWORD);

    public static void insertTaskDB(String title, Integer projectId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(INSERT_TASK_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, title);
            statement.setInt(2, projectId);
            //statement.addBatch();
            //statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
    }

    public static TaskDB getTaskByIdDB(int id) {
        TaskDB task = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_TASK_BY_ID_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                task = new TaskDB(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("project_id"),
                        resultSet.getInt("status_id"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
        return task;
    }

    public static TaskDB getTaskByNameDB(String title) {
        TaskDB task = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_TASK_BY_TITLE_SQL);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                task = new TaskDB(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("project_id"),
                        resultSet.getInt("status_id"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
        return task;
    }

    public static List<TaskDB> getAllTasksDB() {
        List<TaskDB> tasks = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TASKS_SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TaskDB task = new TaskDB(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("project_id"),
                        resultSet.getInt("status_id"));
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
        return tasks;
    }

    public static void closeTaskDB(int id, int isActive) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(CLOSE_TASK_SQL);
            statement.setInt(1, isActive);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
    }

    public static void deleteTaskDB(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(DELETE_TASK_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(EXCEPTION_MSG);
        }
    }
}
