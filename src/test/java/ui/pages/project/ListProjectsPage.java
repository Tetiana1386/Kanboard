package ui.pages.project;

import io.qameta.allure.Step;
import ui.elements.project.ListProjectsElements;
import ui.pages.LoginPage;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class ListProjectsPage extends ListProjectsElements {
    private static final String PROJECTS_PAGE_ENDPOINT = "/projects";
    @Step("Open ListProjects Page")
    public LoginPage openProjectsPage(){
        open(PROJECTS_PAGE_ENDPOINT);
        return new LoginPage();
    }

    @Step("The Projects Page is opened")
    public ListProjectsPage assertProjectsPageIsOpened() {
        getProjectsPage().shouldBe(visible);
        return this;
    }
}
