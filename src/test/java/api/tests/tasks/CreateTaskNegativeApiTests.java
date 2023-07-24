package api.tests.tasks;

import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static utils.MyCustomFaker.getRandomNumber;
import java.util.List;

public class CreateTaskNegativeApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();
    private static final String NAME_PROJECT = "Project" + getRandomNumber();
    private static final String TITLE_TASK = "Task" + getRandomNumber();
    private String projectId;
    private String taskId;
    private String colorId = "green";
    private Integer columnId = 1;
    private Integer ownerId = 1;
    private Integer creatorId = 0;
    private String dateDue = "2023-07-01";
    private String description = "0";
    private Integer categoryId = 0;
    private Integer score = 0;
    private Integer swimLaneId = 1;
    private Integer priority = 1;
    private Integer recurrenceStatus = 0;
    private Integer recurrenceTrigger = 0;
    private Integer recurrenceFactor = 0;
    private Integer recurrenceTimeframe = 0;
    private Integer recurrenceBaseDate = 0;
    private String reference = "0";
    private List<String> tags = List.of("tag_1", "tag_2");
    private String dateStarted = "2023-06-01";

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createNewProject(NAME_PROJECT);
    }

    @Test
    @Description("Checking negative case of creation a task")
    public void createNewTaskNegativeApiTest() {
        taskId = taskApiSteps.createNewTask(TITLE_TASK, Integer.valueOf(projectId), colorId, columnId, ownerId,
                creatorId, dateDue, description, categoryId, score, swimLaneId, priority, recurrenceStatus,
                recurrenceTrigger, recurrenceFactor, recurrenceTimeframe,
                recurrenceBaseDate, reference, tags, dateStarted);

        assertThat(Boolean.valueOf(taskId), is(false));
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));

    }
}
