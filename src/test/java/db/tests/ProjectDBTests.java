package db.tests;

import db.models.ProjectDB;
import db.controllers.ProjectDBController;
import db.controllers.UserDBController;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static api.enums.UserRoles.ADMIN;
import static utils.MyCustomFaker.getRandomNumber;

public class ProjectDBTests {
    private static final String USERNAME = "USER" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    private static final String PROJECT_NAME = "Project" + getRandomNumber();
    private Integer userId;
    private Integer projectId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        UserDBController.insertUserDB(USERNAME, PASSWORD);
        userId = UserDBController.getUserByNameDB(USERNAME).getId();
        UserDBController.updateUserDB(userId, ADMIN.getRole());
    }

    @Test
    public void createProjectDB() {
        ProjectDBController.insertProjectDB(PROJECT_NAME, userId);
        List<ProjectDB> projects = ProjectDBController.getAllProjectsDB();
        Assert.assertTrue(projects.size() > 0);
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        UserDBController.deleteUserDB(userId);
        projectId = ProjectDBController.getProjectByNameDB(PROJECT_NAME).getId();
        ProjectDBController.deleteProjectDB(projectId);
    }
}
