package ui.tests.projects;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.tests.BaseUiTest;

import static api.enums.UserRoles.ADMIN;
import static com.codeborne.selenide.Condition.*;
import static utils.MyCustomFaker.getRandomNumber;

public class CreateProjectPositiveUiTests extends BaseUiTest {
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    String testNameProject = "Test-Project" + getRandomNumber();
    private String userId;
    private String projectIdApi;
    private String projectNameApi;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        userApiSteps.updateUserWithRequiredParameters(Integer.valueOf(userId), ADMIN.getRole());
    }

    @Test
    @Description("Checking positive case of creating a new project with required parameter 'name'")
    public void createNewProjectByAdminUiTest() {
        dashboardPage
                .openDashboardPage()
                .loginByUser(USERNAME, PASSWORD)
                .clickNewProjectLink()
                .assertProjectModalIsOpened();
        projectFormPage.createNewProject(testNameProject)
                .assertProjectPageIsOpened()
                .projectTitle().shouldBe(visible)
                .shouldHave(exactText(testNameProject));

        projectIdApi = projectApiSteps.getProjectByName(testNameProject).getResult().getId();
        projectNameApi = projectApiSteps.getProjectById(Integer.valueOf(projectIdApi)).getResult().getName();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(("http://localhost/project/" + projectIdApi).contains(WebDriverRunner.getWebDriver().getCurrentUrl()));
        softAssert.assertEquals(testNameProject, projectNameApi, "Project names don't match");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectIdApi));
        userApiSteps.deleteUser(Integer.valueOf(userId));
    }
}
