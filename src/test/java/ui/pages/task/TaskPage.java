package ui.pages.task;

import io.qameta.allure.Step;
import ui.elements.task.TaskPageElements;
import ui.pages.LoginPage;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TaskPage extends TaskPageElements {
    private static final String TASK_PAGE_ENDPOINT = "/task/";

    @Step("Open Task Page")
    public LoginPage openTaskPage(Integer taskId){
        open(TASK_PAGE_ENDPOINT + taskId);
        return new LoginPage();
    }

    @Step("Task Page is opened")
    public TaskPage assertTaskPageIsOpen(){
        taskPageSection().shouldBe(visible);
        return this;
    }

    @Step("User clicks on Close this task item")
    public TaskPage clickCloseTaskItem(String item){
        selectElementFromList(itemsActionsTask(), item);
        return this;
    }

    @Step("User clicks on Remove task item")
    public TaskPage clickRemoveTaskItem(){
        removeItem().shouldBe(visible).click();
        return this;
    }

    @Step("User clicks on Yes button")
    public TaskPage clickYesButton(){
        yesButton().shouldBe(visible).click();
        return this;
    }

    @Step("User clicks on Add a comment item")
    public TaskPage clickAddCommentTaskItem(String item){
        selectElementFromList(itemsActionsTask(), item);
        return this;
    }

    @Step("User creates a task's comment and clicks on Save button")
    public TaskPage createTaskComment(String commentText){
        commentArea().shouldBe(visible).sendKeys(commentText);
        saveButtonModal().shouldBe(visible).click();
        return new TaskPage();
    }

    @Step("User clicks on Cancel button")
    public TaskPage cancelTaskComment(){
        cancelButtonModalComment().shouldBe(visible).click();
        return new TaskPage();
    }

    @Step("User clicks on Remove comment item")
    public TaskPage removeTaskComment(){
        commentDropdown().shouldBe(visible).click();
        commentDropdownItem().shouldBe(visible).click();
        return new TaskPage();
    }

    @Step("Confirmation modal window is opened")
    public TaskPage assertModalWindowActionsTaskIsOpen(){
        actionsTaskModalWindow().shouldBe(visible);
        return this;
    }

    @Step("User clicks on Add a sub-task item")
    public TaskPage clickAddSubTaskItem(String item){
        selectElementFromList(itemsActionsTask(), item);
        return new TaskPage();
    }

    @Step("User creates sub-task and clicks on Save button")
    public TaskPage createSubTask(String subTaskTitle){
        subTaskTitleField().shouldBe(visible).sendKeys(subTaskTitle);
        saveButtonModalSubTask().shouldBe(visible).click();
        return new TaskPage();
    }
}
