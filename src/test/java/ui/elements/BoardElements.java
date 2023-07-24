package ui.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ui.pages.AbstractPage;
import static com.codeborne.selenide.Selenide.*;

public class BoardElements extends AbstractPage {
    public SelenideElement addedTask(String taskTitle) {
        return $x(String.format("//a[contains(text(),'%s')]", taskTitle));
    }
    public SelenideElement addedTaskId(String taskId) {
        return $x(String.format("//a[contains(text(),'%s')]", taskId));
    }
    public ElementsCollection configureProjectItem() {
        return $$(".dropdown-submenu-open>li");
    }
    public SelenideElement configureProjectDropdown() {
        return $("[class='fa fa-cog']");
    }
    public SelenideElement newTaskModal() {
        return $("#modal-box");
    }
    public SelenideElement plusButton() {
        return $("a[title='Add a new task'");
    }
    public ElementsCollection boardRows() {
        return $$("tbody tr");
    }
    public SelenideElement dragAndDropTaskBoardOn(String taskId) {
        return $(String.format("div[data-task-id='%s'",taskId));
    }
    public SelenideElement dragAndDropTaskBoardOf() {
        return $x("//*[@id='board']/tbody/tr[2]/td[2]");
    }
}

