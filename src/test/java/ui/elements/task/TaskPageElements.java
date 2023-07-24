package ui.elements.task;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ui.pages.AbstractPage;
import static com.codeborne.selenide.Selenide.*;

public class TaskPageElements extends AbstractPage {
    public SelenideElement taskPageSection() {
        return $("#task-view");
    }
    public ElementsCollection itemsActionsTask() {
        return $$x("//*[@id='task-view']/div[1]/ul[2]/li");
    }
    public SelenideElement actionsTaskModalWindow() {
        return $x("//*[@id='modal-content']");
    }
    public SelenideElement taskStatusClosed() {
        return $x("//span[contains(text(), 'closed')]");
    }
    public SelenideElement yesButton() {
        return $("#modal-confirm-button");
    }
    public SelenideElement removeItem() {
        return $x("//a[text()='Remove']");
    }
    public SelenideElement commentDropdown() {
        return $x("//div[@class='dropdown']");
    }
    public SelenideElement commentDropdownItem() {
        return $x("//*[@id='dropdown']/ul/li[3]/a");
    }
    public SelenideElement commentSection() {
        return $("#comment-3");
    }
    public SelenideElement commentArea() {
        return $x("//*[@id='modal-content']/form/div[1]/div/div[2]/textarea");
    }
    public SelenideElement commentText(String commentText) {
        return $x(String.format("//p[contains(text(),'%s')]", commentText));
    }
    public SelenideElement saveButtonModal() {
        return $x("//*[@id='modal-content']/form/div[2]/div/button");
    }
    public SelenideElement cancelButtonModalComment() {
        return $(".form-actions>a");
    }
    public SelenideElement subTaskTitleField() {
        return $("#form-title");
    }
    public SelenideElement subTaskTable() {
        return $x("//*[@id='task-view']/div[2]/details[2]/div/table");
    }
    public SelenideElement saveButtonModalSubTask() {
        return $x("//*[@id='modal-content']/form/div/div/button");
    }
}
