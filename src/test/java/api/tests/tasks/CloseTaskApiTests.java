package api.tests.tasks;

import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.MyCustomFaker.getRandomNumber;

public class CloseTaskApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();
    private static final String NAME_PROJECT = "Project" + getRandomNumber();
    private static final String TITLE_TASK = "Task" + getRandomNumber();
    private String projectId;
    private String taskId;
    private Boolean isClosed;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createNewProject(NAME_PROJECT);
        taskId = taskApiSteps.createNewTaskWithRequiredParameters(TITLE_TASK, Integer.valueOf(projectId));
    }
    @Test
    public void closeTasksApiTest() {
        isClosed = taskApiSteps.closeTask(Integer.valueOf(taskId));
        Assert.assertTrue(isClosed, "Task isn't closed");
    }

    @AfterMethod(alwaysRun = true)
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        taskApiSteps.deleteTask(Integer.valueOf(taskId));
    }
}
