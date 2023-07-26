package ui.tests.tasks;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.tests.BaseUiTest;

import static api.enums.UserRoles.ADMIN;
import static com.codeborne.selenide.Condition.*;
import static utils.MyCustomFaker.getRandomNumber;

public class TaskUiTests extends BaseUiTest {
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    String testNameProject = "Project" + getRandomNumber();
    String taskTitle = "Task" + getRandomNumber();
    String commentText = getRandomNumber() + "#Comment Text";
    String subTaskTitle = "Sub-task" + getRandomNumber();
    private String userId;
    private String projectId;
    private String taskId;

    @BeforeMethod
    @Description("Prepare data for test")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        userApiSteps.updateUserWithRequiredParameters(Integer.valueOf(userId), ADMIN.getRole());
        projectId = projectApiSteps.createNewProject(testNameProject);
        taskId = taskApiSteps.createNewTaskWithRequiredParameters(taskTitle, Integer.valueOf(projectId));
    }

    @Test
    @Description("Checking positive case of closing a task")
    public void closeTaskUiTest() {
        taskPage.openTaskPage(Integer.valueOf(taskId))
                .loginOnTaskPage(USERNAME, PASSWORD)
                .clickCloseTaskItem("Close this task")
                .assertModalWindowActionsTaskIsOpen()
                .clickYesButton()
                .taskStatusClosed().shouldBe(visible)
                .shouldHave(exactText("closed"));

        Assert.assertEquals(taskApiSteps.getTaskById(Integer.valueOf(taskId)).getResult().getIs_active(), "0");
    }

    @Test
    @Description("Checking positive case of removing a task")
    public void deleteTaskUiTest() {
        taskPage
                .openTaskPage(Integer.valueOf(taskId))
                .loginOnTaskPage(USERNAME, PASSWORD)
                .clickRemoveTaskItem()
                .assertModalWindowActionsTaskIsOpen()
                .clickYesButton();
        boardPage
                .addedTaskId(taskId)
                .should(disappear);

        Assert.assertNull(taskApiSteps.getTaskById(Integer.valueOf(taskId)).getResult());
    }

    @Test
    @Description("Checking positive case of adding a comment")
    public void addCommentTaskUiTest() {
        taskPage
                .openTaskPage(Integer.valueOf(taskId))
                .loginOnTaskPage(USERNAME, PASSWORD)
                .clickAddCommentTaskItem("Add a comment")
                .assertModalWindowActionsTaskIsOpen()
                .createTaskComment(commentText)
                .commentText(commentText).shouldBe(visible)
                .shouldHave(text(commentText));
    }

    @Test
    @Description("Checking positive case of cancel a comment")
    public void cancelCommentTaskUiTest() {
        taskPage
                .openTaskPage(Integer.valueOf(taskId))
                .loginOnTaskPage(USERNAME, PASSWORD)
                .clickAddCommentTaskItem("Add a comment")
                .assertModalWindowActionsTaskIsOpen()
                .cancelTaskComment()
                .assertTaskPageIsOpen();
    }

    @Test
    @Description("Checking positive case of remove a comment")
    public void removeCommentTaskUiTest() {
        taskPage
                .openTaskPage(Integer.valueOf(taskId))
                .loginOnTaskPage(USERNAME, PASSWORD)
                .clickAddCommentTaskItem("Add a comment")
                .assertModalWindowActionsTaskIsOpen()
                .createTaskComment(commentText)
                .removeTaskComment()
                .commentSection().shouldBe(disappear);
    }

    @Test
    @Description("Checking positive case of adding a sub-task")
    public void addSubTaskUiTest() {
        taskPage
                .openTaskPage(Integer.valueOf(taskId))
                .loginOnTaskPage(USERNAME, PASSWORD)
                .clickAddSubTaskItem("Add a sub-task")
                .assertModalWindowActionsTaskIsOpen()
                .createSubTask(subTaskTitle)
                .subTaskTable().shouldBe(visible);
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        userApiSteps.deleteUser(Integer.valueOf(userId));
    }
}
