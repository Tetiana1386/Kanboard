package ui.pages;

import io.qameta.allure.Step;
import ui.elements.BoardElements;
import ui.pages.task.TaskPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class BoardPage extends BoardElements {
    private static final String BOARD_PAGE_ENDPOINT = "/board/";

    @Step("Open Board Page")
    public LoginPage openBoardPage(Integer projectId) {
        open(BOARD_PAGE_ENDPOINT + projectId);
        return new LoginPage();
    }

    @Step("User clicks on configure project dropdown")
    public BoardPage clickConfigureProjectDropdown() {
        configureProjectDropdown().shouldBe(visible).click();
        return new BoardPage();
    }

    @Step("User clicks on item configure project dropdown")
    public BoardPage clickItemConfigureProject(String searchItem) {
        selectElementFromList(configureProjectItem(), searchItem);
        return new BoardPage();
    }

    @Step("User clicks on Plus Button")
    public BoardPage clickPlusOnBoardColumn() {
        plusButton().shouldBe(visible).click();
        return new BoardPage();
    }

    @Step("Task's modal window is opened")
    public TaskPage assertNewTaskModalIsOpen() {
        newTaskModal().shouldBe(visible);
        return new TaskPage();
    }
}

