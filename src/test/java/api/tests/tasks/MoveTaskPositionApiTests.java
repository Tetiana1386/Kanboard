package api.tests.tasks;

import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static utils.MyCustomFaker.getRandomNumber;

public class MoveTaskPositionApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();
    private static final String NAME_PROJECT = "Project" + getRandomNumber();
    private static final String TITLE_TASK = "Task" + getRandomNumber();
    private String projectId;
    private String taskId;
    private boolean isMoved;


    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createNewProject(NAME_PROJECT);
        taskId = taskApiSteps.createNewTaskWithRequiredParameters(TITLE_TASK, Integer.valueOf(projectId));
    }
    @Test
    @Description("Checking that the task can be moved")
    public void moveTaskPositionApiTest() {
        Integer columnId = 2;
        Integer position = 1;
        Integer swimLaneId = Integer.valueOf(projectId);

        isMoved = taskApiSteps.moveTaskPosition(Integer.valueOf(projectId), Integer.valueOf(taskId), columnId, position, swimLaneId);
        Assert.assertFalse(isMoved, "The task has been moved");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        taskApiSteps.deleteTask(Integer.valueOf(taskId));
    }
}
