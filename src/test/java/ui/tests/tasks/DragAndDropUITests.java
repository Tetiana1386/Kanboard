package ui.tests.tasks;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.tests.BaseUiTest;

import static api.enums.UserRoles.ADMIN;
import static com.codeborne.selenide.Selenide.actions;
import static utils.MyCustomFaker.getRandomNumber;

public class DragAndDropUITests extends BaseUiTest {
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    String testNameProject = "Project" + getRandomNumber();
    String taskTitle = "Task" + getRandomNumber();
    private String userId;
    private String projectId;
    private String taskId;

    @BeforeMethod
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        userApiSteps.updateUserWithRequiredParameters(Integer.valueOf(userId), ADMIN.getRole());
        projectId = projectApiSteps.createNewProject(testNameProject);
        taskId = taskApiSteps.createNewTaskWithRequiredParameters(taskTitle, Integer.valueOf(projectId));
    }

    @Test
    @Description("Checking the positive case of drag and drop task on the board")
    public void dragAndDropBoardUiTest() {
        boardPage
                .openBoardPage(Integer.valueOf(projectId))
                .loginOnBoardPage(USERNAME, PASSWORD);
        actions().clickAndHold(boardPage.dragAndDropTaskBoardOn(taskId).shouldBe(Condition.visible))
                .moveToElement(boardPage.dragAndDropTaskBoardOf().shouldBe(Condition.visible))
                .release()
                .build()
                .perform();
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        taskApiSteps.deleteTask(Integer.valueOf(taskId));
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        userApiSteps.deleteUser(Integer.valueOf(userId));
    }
}
