package ui.pages.task;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.elements.task.TaskFormElements;
import ui.pages.BoardPage;
import static com.codeborne.selenide.Condition.visible;

public class TaskFormPage extends TaskFormElements {
    @Step("User creates a new task with a task's name {0} and clicks on Save button")
    public BoardPage createTask(String nameTask) {
        taskName().shouldBe(visible).sendKeys(nameTask);
        saveTaskButton().shouldBe(visible).click();
        return new BoardPage();
    }

    @Step("User creates a new task with a task's name {0},color{1},assignee{2},column{3},priority{4} and other params, and clicks on Save button")
    public BoardPage createTaskWithSomeParams(String task, String color, String assignee,
                                              String column, String priority,
                                              String DueMonth, String DueYear, String DueDay,
                                              String StMonth, String StYear, String StDay) {
        taskName().shouldBe(visible).sendKeys(task);
        selectColor().shouldBe(visible).selectOptionContainingText(color);
        selectAssignee().shouldBe(visible).selectOptionContainingText(assignee);
        selectColumn().shouldBe(visible).selectOptionContainingText(column);
        selectPriority().shouldBe(visible).selectOptionContainingText(priority);
        clickFieldDate(selectDueDate());
        selectCalendar(DueMonth, DueYear, DueDay);
        clickFieldDate(selectStartDate());
        selectCalendar(StMonth, StYear, StDay);
        saveTaskButton().shouldBe(visible).click();
        return new BoardPage();
    }

    public void clickFieldDate(SelenideElement date) {
        date.shouldBe(visible).click();
    }

    public void selectCalendar(String month, String year, String day) {
        while(selectMonth().shouldBe(visible).getText().contains(month)
                && selectYear().shouldBe(visible).getText().contains(year)) {
            selectArrow().shouldBe(visible).click();
        }
        selectElementFromList(selectDay(),day);
        doneBtnCalendar().shouldBe(visible).click();
    }

    public TaskFormPage clickSaveTaskButton() {
        saveTaskButton().shouldBe(visible).click();
        return new TaskFormPage();
    }
}
