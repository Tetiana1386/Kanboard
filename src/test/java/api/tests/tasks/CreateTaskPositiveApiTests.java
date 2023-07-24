package api.tests.tasks;

import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import static utils.MyCustomFaker.getRandomNumber;

public class CreateTaskPositiveApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();
    private static final String NAME_PROJECT = "Project" + getRandomNumber();
    private static final String TITLE_TASK = "Task" + getRandomNumber();
    private String projectId;
    private String taskId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createNewProject(NAME_PROJECT);
    }

    @Test
    @Description("Checking positive case of creation a task")
    public void createNewTaskPositiveApiTest() {
        taskId = taskApiSteps.createNewTaskWithRequiredParameters(TITLE_TASK, Integer.valueOf(projectId));
        Assert.assertNotNull(taskId, "Task isn't created");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        taskApiSteps.deleteTask(Integer.valueOf(taskId));
    }
}
