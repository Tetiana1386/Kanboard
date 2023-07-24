package ui.tests.projects;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.tests.BaseUiTest;
import static api.enums.UserRoles.ADMIN;
import static utils.MyCustomFaker.getRandomNumber;

public class CancelDeleteProjectUiTests extends BaseUiTest {
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    String testNameProject = "Test-Project" + getRandomNumber();
    private String userId;
    private String projectId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        userApiSteps.updateUserWithRequiredParameters(Integer.valueOf(userId), ADMIN.getRole());
        projectId = projectApiSteps.createNewProject(testNameProject);
    }

    @Test
    @Description("Checking the cancellation of remove project by clicking on a button Cancel")
    public void removeProjectUiTest() {
        projectPage
                .openProjectPage(Integer.valueOf(projectId))
                .loginOnProjectPage(USERNAME, PASSWORD)
                .cancelRemoveProject()
                .assertProjectPageIsOpened();
        Assert.assertEquals(projectPage.getProjectTitle(), testNameProject, "The project has been deleted");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        userApiSteps.deleteUser(Integer.valueOf(userId));
    }
}
