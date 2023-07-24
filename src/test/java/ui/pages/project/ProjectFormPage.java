package ui.pages.project;

import io.qameta.allure.Step;
import ui.elements.project.ProjectFormElements;
import ui.pages.DashboardPage;
import static com.codeborne.selenide.Condition.visible;

public class ProjectFormPage extends ProjectFormElements {

    @Step("User creates a new project with a {0} and clicks on Save button")
    public ProjectPage createNewProject(String projectName) {
        projectNameField().shouldBe(visible).sendKeys(projectName);
        saveButton().shouldBe(visible).click();
        return new ProjectPage();
    }

    @Step("User creates a new project with empty name and clicks on Save button")
    public DashboardPage createNewProjectWithEmptyValue() {
        saveButton().shouldBe(visible).click();
        return new DashboardPage();
    }

    @Step("User clicks on Cancel button")
    public DashboardPage clickCancelButton() {
        cancelButton().shouldBe(visible).click();
        return new DashboardPage();
    }

    @Step("User clicks on CloseModal button")
    public DashboardPage clickCloseModalButton() {
        closeModalButton().shouldBe(visible).click();
        return new DashboardPage();
    }

    @Step("User gets message with error ")
    public String getFormError() {
        return formError().shouldBe(visible).getText();
    }
}
