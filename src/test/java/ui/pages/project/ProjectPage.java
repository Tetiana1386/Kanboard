package ui.pages.project;

import io.qameta.allure.Step;
import ui.elements.project.ProjectPageElements;
import ui.pages.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class ProjectPage extends ProjectPageElements {
    private static final String PROJECT_PAGE_ENDPOINT = "/project/";

    @Step("Open Project Page")
    public LoginPage openProjectPage(Integer projectId) {
        open(PROJECT_PAGE_ENDPOINT + projectId);
        return new LoginPage();
    }

    @Step("Project Page is opened")
    public ProjectPage assertProjectPageIsOpened() {
        projectPageContainer().shouldBe(visible);
        return new ProjectPage();
    }

    @Step("Received the title ")
    public String getProjectTitle() {
        return projectTitle().shouldBe(visible).getText();
    }

    @Step("User clicks on Remove item")
    public void clickOnElementRemoveProject() {
        actionItem("Remove").shouldBe(visible).click();
    }

    @Step("Received a modal confirmation window")
    public ProjectPage assertProjectRemoveModalIsOpened() {
        removeProjectModal().shouldBe(visible);
        return new ProjectPage();
    }

    @Step("User clicks on Remove item, appears confirmation modal window and clicks on Yes button")
    public ListProjectsPage removeProject() {
        clickOnElementRemoveProject();
        assertProjectRemoveModalIsOpened();
        yesButton().shouldBe(visible).click();
        return new ListProjectsPage();
    }

    @Step("User clicks on Remove item, appears confirmation modal window and clicks on Cancel button")
    public ProjectPage cancelRemoveProject() {
        clickOnElementRemoveProject();
        assertProjectRemoveModalIsOpened();
        cancelButtonRemoveProject().shouldBe(visible).click();
        return new ProjectPage();
    }
}
