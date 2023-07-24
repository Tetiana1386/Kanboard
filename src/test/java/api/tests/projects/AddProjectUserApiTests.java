package api.tests.projects;

import api.steps.ProjectApiSteps;
import api.steps.UserApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.enums.ProjectRoles.MEMBER;
import static utils.MyCustomFaker.getRandomNumber;

public class AddProjectUserApiTests {
    UserApiSteps userApiSteps = new UserApiSteps();
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    private static final String PROJECT_NAME = "Project" + getRandomNumber();
    private String userId;
    private String projectId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        projectId = projectApiSteps.createNewProject(PROJECT_NAME);
    }

    @Test
    @Description("Checking positive case of adding a project user")
    public void addProjectUserPositiveApiTest(){
        boolean result = projectApiSteps.addProjectUser(projectId, userId, MEMBER.getRoleProject());
        Assert.assertTrue(result, "Project user isn't added");
    }

    @Test
    @Description("checking negative case of adding a project user")
    public void addProjectUserNegativeApiTest(){
        boolean result = projectApiSteps.addProjectUser(projectId+projectId, userId, MEMBER.getRoleProject());
        Assert.assertFalse(result, "Project user is added");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleanup test data")
    public void removeDataAfterTest(){
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        userApiSteps.deleteUser(Integer.valueOf(userId));
    }
}
