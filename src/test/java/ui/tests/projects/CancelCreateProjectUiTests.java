package ui.tests.projects;

import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.tests.BaseUiTest;

import static api.enums.UserRoles.ADMIN;
import static utils.MyCustomFaker.getRandomNumber;

public class CancelCreateProjectUiTests extends BaseUiTest {
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    private String userId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        userApiSteps.updateUserWithRequiredParameters(Integer.valueOf(userId), ADMIN.getRole());
    }

    @Test
    @Description("Checking the cancellation of project creation by clicking on a button Cancel")
    public void canselCreateProjectUiTest() {
        dashboardPage
                .openDashboardPage()
                .loginByUser(USERNAME, PASSWORD)
                .clickNewProjectLink()
                .assertProjectModalIsOpened();
        projectFormPage
                .clickCancelButton()
                .assertDashboardSectionIsOpened();
    }

    @Test
    @Description("Checking the cancellation of project creation by clicking on a button Close modal")
    public void closeModalCreateProjectUiTest() {
        dashboardPage
                .openDashboardPage()
                .loginByUser(USERNAME, PASSWORD)
                .clickNewProjectLink()
                .assertProjectModalIsOpened();
        projectFormPage
                .clickCloseModalButton()
                .assertDashboardSectionIsOpened();
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        userApiSteps.deleteUser(Integer.valueOf(userId));

    }
}
