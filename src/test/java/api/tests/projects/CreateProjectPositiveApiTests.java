package api.tests.projects;

import api.steps.ProjectApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static utils.MyCustomFaker.getRandomNumber;

public class CreateProjectPositiveApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private static final String PROJECT_NAME = "Project" + getRandomNumber();
    private String projectId;

    @Test
    @Description("Checking positive case of creating project")
    public void createProjectWithValidDataApiTest() {
        projectId = projectApiSteps.createNewProject(PROJECT_NAME);
        Assert.assertNotNull(projectId, "Project isn't created");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));

    }
}
