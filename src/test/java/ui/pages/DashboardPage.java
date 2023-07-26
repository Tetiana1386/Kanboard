package ui.pages;

import io.qameta.allure.Step;
import ui.elements.DashboardElements;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage extends DashboardElements {
    private static final String DASHBOARD_PAGE_ENDPOINT = "/dashboard";

    @Step("Open DashBoard Page")
    public LoginPage openDashboardPage() {
        open(DASHBOARD_PAGE_ENDPOINT);
        return new LoginPage();
    }

    @Step("DashBoard Page is opened")
    public DashboardPage assertDashboardSectionIsOpened() {
        dashboardSection().shouldBe(visible);
        return this;
    }

    @Step("Project's modal is opened")
    public DashboardPage assertProjectModalIsOpened() {
        newProjectModal().shouldBe(visible);
        return new DashboardPage();
    }

    @Step("User clicks on new project link")
    public DashboardPage clickNewProjectLink() {
        newProjectLink().shouldBe(visible).click();
        return this;
    }
}
