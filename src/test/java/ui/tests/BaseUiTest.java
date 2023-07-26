package ui.tests;

import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import api.steps.UserApiSteps;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import io.qameta.allure.Description;
import org.testng.annotations.*;
import ui.pages.*;
import ui.pages.project.ListProjectsPage;
import ui.pages.project.ProjectFormPage;
import ui.pages.project.ProjectPage;
import ui.pages.task.TaskFormPage;
import ui.pages.task.TaskPage;

import static com.codeborne.selenide.WebDriverRunner.closeWindow;
import static utils.EnvProperties.BASE_URL;

public class BaseUiTest {

    protected UserApiSteps userApiSteps;
    protected ProjectApiSteps projectApiSteps;
    protected TaskApiSteps taskApiSteps;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected ProjectPage projectPage;
    protected ProjectFormPage projectFormPage;
    protected ListProjectsPage projectsPage;
    protected BoardPage boardPage;
    protected TaskPage taskPage;
    protected TaskFormPage taskFormPage;

    @BeforeMethod(alwaysRun = true)
    @Description("User opens a browser")
    public void setUp() {

        String browser = System.getProperty("browser");
        //String headless = System.getProperty("headless");
        //Configuration.browser = browser;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1440x900";
        Configuration.baseUrl = BASE_URL;

//        if(headless.equals("true")){
//            Configuration.headless = true;
//        }

        userApiSteps = new UserApiSteps();
        projectApiSteps = new ProjectApiSteps();
        taskApiSteps = new TaskApiSteps();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        projectPage = new ProjectPage();
        projectFormPage = new ProjectFormPage();
        projectsPage = new ListProjectsPage();
        boardPage = new BoardPage();
        taskPage = new TaskPage();
        taskFormPage = new TaskFormPage();
    }

    @AfterMethod(alwaysRun = true)
    @Description("User closes a browser")
    public void cleanUp() {
        closeWindow();
        closeWebDriver();
    }
}
