package ui.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import io.qameta.allure.Step;
import ui.elements.LoginElements;
import ui.pages.project.ProjectPage;
import ui.pages.task.TaskPage;

public class LoginPage extends LoginElements {
    @Step("Open the Login page")
    public LoginPage openLoginPage() {
        open("");
        return new LoginPage();
    }

    @Step("User fills login {0} and password {1} and clicks on Submit button")
    public void fillInInputFieldLogin(String username, String password) {
        userField().shouldBe(visible).sendKeys(username);
        passwordField().shouldBe(visible).sendKeys(password);
        submitButton().shouldBe(visible).click();
    }

    @Step("User fills login {0} and password {1} and clicks on Submit button")
    public DashboardPage loginByUser(String username, String password) {
        fillInInputFieldLogin(username, password);
        return new DashboardPage();
    }

    @Step("User fills login {0} and password {1} and clicks on Submit button")
    public ProjectPage loginOnProjectPage(String username, String password) {
        fillInInputFieldLogin(username, password);
        return new ProjectPage();
    }

    @Step("User fills login {0} and password {1} and clicks on Submit button")
    public TaskPage loginOnTaskPage(String username, String password) {
        fillInInputFieldLogin(username, password);
        return new TaskPage();
    }

    @Step("User fills login {0} and password {1} and clicks on Submit button")
    public BoardPage loginOnBoardPage(String username, String password) {
        fillInInputFieldLogin(username, password);
        return new BoardPage();
    }
}
