package api.tests.projects;

import api.steps.ProjectApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.MyCustomFaker.getRandomNumber;

public class DeleteProjectPositiveApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private static final String NAME_PROJECT = "Project" + + getRandomNumber();
    private String projectId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createNewProject(NAME_PROJECT);
        System.out.println(projectId);
    }
    @Test
    @Description("Checking positive case of removing project")
    public void deleteProjectWithValidDataApiTest() {
        boolean result = projectApiSteps.deleteProject(Integer.valueOf(projectId));
        Assert.assertTrue(Boolean.valueOf(result), "Project isn't removed");
    }
}
