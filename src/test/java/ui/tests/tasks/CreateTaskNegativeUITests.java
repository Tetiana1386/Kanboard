package ui.tests.tasks;

import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.tests.BaseUiTest;
import static api.enums.UserRoles.ADMIN;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static utils.MyCustomFaker.getRandomNumber;

public class CreateTaskNegativeUITests extends BaseUiTest {
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    String testNameProject = "Project" + getRandomNumber();
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
    @Description("Checking negative case of creating a new task with required parameter 'title'")
    public void createTaskFromDropdownUiTest() {
        boardPage
                .openBoardPage(Integer.valueOf(projectId))
                .loginOnBoardPage(USERNAME, PASSWORD)
                .clickPlusOnBoardColumn()
                .assertNewTaskModalIsOpen();
        taskFormPage
                .clickSaveTaskButton()
                .errorMsgTask().shouldBe(visible)
                .shouldHave(exactText("The title is required"));
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        userApiSteps.deleteUser(Integer.valueOf(userId));
    }
}
