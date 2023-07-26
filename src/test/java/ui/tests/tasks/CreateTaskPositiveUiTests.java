package ui.tests.tasks;

import api.steps.BoardApiSteps;
import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.tests.BaseUiTest;

import static api.enums.UserRoles.ADMIN;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static utils.MyCustomFaker.getRandomNumber;

public class CreateTaskPositiveUiTests extends BaseUiTest {
    BoardApiSteps boardApiSteps = new BoardApiSteps();
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    String testNameProject = "Project" + getRandomNumber();
    String taskTitle = "Task" + getRandomNumber();
    private String userId;
    private String projectId;
    private String addedTaskTitleApi;
    private String addedTaskIdApi;

    @BeforeMethod
    @Description("Prepare data for test")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        userApiSteps.updateUserWithRequiredParameters(Integer.valueOf(userId), ADMIN.getRole());
        projectId = projectApiSteps.createNewProject(testNameProject);
        boardPage
                .openBoardPage(Integer.valueOf(projectId))
                .loginOnBoardPage(USERNAME, PASSWORD);
    }

    @Test
    @Description("Checking positive case of creating a new task with required parameter 'title'")
    public void createTaskFromDropdownUiTest() {
        boardPage
                .clickConfigureProjectDropdown()
                .clickItemConfigureProject("Add a new task")
                .assertNewTaskModalIsOpen();
        taskFormPage
                .createTask(taskTitle)
                .addedTask(taskTitle).shouldBe(visible)
                .shouldHave(exactText(taskTitle));

        addedTaskTitleApi = boardApiSteps.getBoardForProject(Integer.valueOf(projectId))
                .getResult().get(0)
                .getColumns().get(0)
                .getTasks().get(0)
                .getTitle();
        addedTaskIdApi = boardApiSteps.getBoardForProject(Integer.valueOf(projectId))
                .getResult().get(0)
                .getColumns().get(0)
                .getTasks().get(0)
                .getId();

        boardPage.boardRows().shouldHave(CollectionCondition.sizeGreaterThan(0));
        Assert.assertEquals(addedTaskTitleApi, taskTitle, "The task hasn't been added to the database");
    }

    @Test
    @Description("Checking positive case of creating a new task with all parameters from board column")
    public void createTaskFromBoardColumnTitleUiTest() {
        boardPage
                .clickPlusOnBoardColumn()
                .assertNewTaskModalIsOpen();
        taskFormPage
                .createTaskWithSomeParams(taskTitle, "Green",
                        "Unassigned", "Backlog", "1",
                        "July", "2023", "25",
                        "August", "2023", "10")
                .addedTask(taskTitle).shouldBe(visible)
                .shouldHave(exactText(taskTitle));

        addedTaskTitleApi = boardApiSteps.getBoardForProject(Integer.valueOf(projectId))
                .getResult().get(0)
                .getColumns().get(0)
                .getTasks().get(0)
                .getTitle();
        addedTaskIdApi = boardApiSteps.getBoardForProject(Integer.valueOf(projectId))
                .getResult().get(0)
                .getColumns().get(0)
                .getTasks().get(0)
                .getId();

        boardPage.boardRows().shouldHave(CollectionCondition.sizeGreaterThan(0));
        Assert.assertEquals(addedTaskTitleApi, taskTitle, "The task hasn't been added to the database");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        taskApiSteps.deleteTask(Integer.valueOf(addedTaskIdApi));
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        userApiSteps.deleteUser(Integer.valueOf(userId));
    }
}
