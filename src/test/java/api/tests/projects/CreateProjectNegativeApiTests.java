package api.tests.projects;

import api.steps.ProjectApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateProjectNegativeApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private static final String PROJECT_NAME = "";
    private String projectId;
    @Test
    @Description("Checking negative case of creating project")
    public void createProjectWithInvalidDataApiTest() {
        projectId = projectApiSteps.createNewProject(PROJECT_NAME);
        Assert.assertFalse(Boolean.valueOf(projectId), "Project is created");
    }
}
