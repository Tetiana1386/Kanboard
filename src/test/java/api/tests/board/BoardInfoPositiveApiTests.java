package api.tests.board;

import api.models.BodyResult;
import api.models.board.BoardProperties;
import api.steps.BoardApiSteps;
import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static utils.MyCustomFaker.getRandomNumber;

public class BoardInfoPositiveApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();
    BoardApiSteps boardApiSteps = new BoardApiSteps();

    private static final String NAME_PROJECT = "Project" + getRandomNumber();
    private static final String TITLE_TASK = "Task" + getRandomNumber();
    private String projectId;
    private String taskId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createNewProject(NAME_PROJECT);
        taskId = taskApiSteps.createNewTaskWithRequiredParameters(TITLE_TASK, Integer.valueOf(projectId));
    }

    @Test
    @Description("Checking positive case of creation info on the board")
    public void checkBoardApiTest() {
        BodyResult<List<BoardProperties>> boardInfoBodyResult = boardApiSteps.getBoardForProject(Integer.valueOf(projectId));
        Assert.assertTrue(boardInfoBodyResult.getResult().size() > 0, "Board request doesn't contain records");
        String boardTaskTitle = boardInfoBodyResult.getResult().get(0).getColumns().get(0).getTasks().get(0).getTitle();
        Assert.assertEquals(boardTaskTitle, TITLE_TASK, "The task isn't displayed on the board");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        taskApiSteps.deleteTask(Integer.valueOf(taskId));
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
    }
}

