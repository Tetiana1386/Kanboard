package api.tests.projects;

import api.models.BodyResult;
import api.models.projects.ProjectProperties;
import api.steps.ProjectApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import static utils.MyCustomFaker.getRandomNumber;

public class GetProjectApiTests {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private static final String NAME_PROJECT = "Project" + + getRandomNumber();
    private String projectId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createNewProject(NAME_PROJECT);
    }

    @Test
    @Description("Checking negative case of getting project by id")
    public void getProjectByIdNegativeApiTest() {
        BodyResult<ProjectProperties> projectProperties = projectApiSteps.getProjectById(Integer.valueOf(projectId + projectId));
        Assert.assertNull(projectProperties.getResult(), "Found a project with some data");
    }
    @Test
    @Description("Checking positive case of getting project by id")
    public void getProjectByIdPositiveApiTest() {
        BodyResult<ProjectProperties> projectProperties = projectApiSteps.getProjectById(Integer.valueOf(projectId));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(Integer.valueOf(projectProperties.getResult().getId()), Integer.valueOf(projectId), "No project found with this id");
        softAssert.assertEquals(projectProperties.getResult().getName(), NAME_PROJECT, "No project found with this name");
        softAssert.assertAll();
    }

    @Test
    @Description("Checking positive case of getting project by name")
    public void getProjectByNamePositiveApiTest() {
        BodyResult<ProjectProperties> projectProperties = projectApiSteps.getProjectByName(NAME_PROJECT);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(Integer.valueOf(projectProperties.getResult().getId()), Integer.valueOf(projectId), "No project found with this id");
        softAssert.assertEquals(projectProperties.getResult().getName(), NAME_PROJECT, "No project found with this name");
        softAssert.assertAll();
    }

    @Test
    @Description("Checking positive case of getting all projects")
    public void getAllProjectsPositiveApiTest() {
        BodyResult<List<ProjectProperties>> projectPropertiesList = projectApiSteps.getAllProjects();
        Assert.assertTrue(projectPropertiesList.getResult().size() > 0, "List of projects is empty");
    }

    @Test
    @Description("Checking negative case of getting all projects")
    public void getAllProjectsNegativeApiTest() {
        BodyResult<List<ProjectProperties>> projectPropertiesList = projectApiSteps.getAllProjects();
        Assert.assertFalse(Boolean.valueOf(String.valueOf(projectPropertiesList)), "List of projects isn't empty");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        projectApiSteps.deleteProject(Integer.valueOf(projectId));

    }

}
