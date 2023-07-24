package api.tests.projects;

import api.steps.ProjectApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.MyCustomFaker.getRandomNumber;

public class DeleteProjectNegativeApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private static final String NAME_PROJECT = "Project" + getRandomNumber();
    private String projectId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createNewProject(NAME_PROJECT);
    }
    @Test
    @Description("Checking negative case of removing project")
    public void deleteProjectWithValidDataApiTest() {
        boolean result = projectApiSteps.deleteProject(Integer.valueOf(projectId + projectId));
        Assert.assertFalse(Boolean.valueOf(result), "Project is removed");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));

    }
}
