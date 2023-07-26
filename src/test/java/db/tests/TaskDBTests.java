package db.tests;

import db.controllers.ProjectDBController;
import db.controllers.TaskDBController;
import db.controllers.UserDBController;
import db.models.TaskDB;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.enums.UserRoles.ADMIN;
import static utils.MyCustomFaker.getRandomNumber;

public class TaskDBTests {
    private static final String USERNAME = "USER" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    private static final String PROJECT_NAME = "Project" + getRandomNumber();
    private static final String TASK_TITLE = "Task" + getRandomNumber();
    private Integer userId;
    private Integer projectId;
    private Integer taskId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        UserDBController.insertUserDB(USERNAME, PASSWORD);
        userId = UserDBController.getUserByNameDB(USERNAME).getId();
        UserDBController.updateUserDB(userId, ADMIN.getRole());

        ProjectDBController.insertProjectDB(PROJECT_NAME, userId);
        projectId = ProjectDBController.getProjectByNameDB(PROJECT_NAME).getId();
    }

//    @Test
//    public void createTaskDB() {
//        TaskDBController.insertTaskDB(TASK_TITLE, projectId);
//    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
//        taskId = TaskDBController.getTaskByNameDB(TASK_TITLE).getId();
//        TaskDBController.deleteTaskDB(taskId);
        projectId = ProjectDBController.getProjectByNameDB(PROJECT_NAME).getId();
        ProjectDBController.deleteProjectDB(projectId);
        UserDBController.deleteUserDB(userId);
    }
}
