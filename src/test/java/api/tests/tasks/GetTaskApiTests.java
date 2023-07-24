package api.tests.tasks;

import api.models.BodyResult;
import api.models.tasks.TaskProperties;
import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
import static utils.MyCustomFaker.getRandomNumber;

public class GetTaskApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();
    BodyResult<TaskProperties> taskProperties;
    private String projectId;
    private String taskId;
    private static final String NAME_PROJECT = "Project" + getRandomNumber();
    private static final String TITLE_TASK = "Task" + getRandomNumber();


    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createNewProject(NAME_PROJECT);
        taskId = taskApiSteps.createNewTaskWithRequiredParameters(TITLE_TASK, Integer.valueOf(projectId));
    }

    @Test
    @Description("Checking negative case of getting a task")
    public void getTaskNegativeApiTest() {
        taskProperties = taskApiSteps.getTaskById(Integer.valueOf(taskId + taskId));
        Assert.assertNull(taskProperties.getResult(), "Received task properties");
    }


    @Test
    @Description("Checking positive case of getting a task")
    public void getTaskPositiveApiTest() {
        taskProperties = taskApiSteps.getTaskById(Integer.valueOf(taskId));
        Assert.assertNotNull(taskProperties.getResult(), "No task properties received");
    }

    @Test
    @Description("Checking negative case of getting all tasks")
    public void getAllTasksNegativeApiTest() {
        BodyResult<List<TaskProperties>> result = taskApiSteps.getAllTasks(Integer.valueOf(projectId), 1);
        Assert.assertFalse(Boolean.valueOf(result.getResult().toString()), "List of tasks isn't empty");
    }

    @Test
    @Description("Checking positive case of getting all tasks")
    public void getAllTasksPositiveApiTest() {
        BodyResult<List<TaskProperties>> taskPropertiesList = taskApiSteps.getAllTasks(Integer.valueOf(projectId), 1);
        Assert.assertTrue(taskPropertiesList.getResult().size() > 0, "List of tasks is empty");
    }

    @AfterMethod(alwaysRun = true)
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));
        taskApiSteps.deleteTask(Integer.valueOf(taskId));
    }
}
