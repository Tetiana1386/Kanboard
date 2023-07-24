package ui.elements.task;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ui.pages.AbstractPage;
import static com.codeborne.selenide.Selenide.*;

public class TaskFormElements extends AbstractPage {
    public SelenideElement taskName() {
        return $("#form-title");
    }
    public SelenideElement selectColor() {
        return $("#form-color_id");
    }
    public SelenideElement selectAssignee() {
        return $("#form-owner_id");
    }
    public SelenideElement selectColumn() {
        return $("#form-column_id");
    }
    public SelenideElement selectPriority() {
        return $("#form-priority");
    }
    public SelenideElement selectDueDate() {
        return $("#form-date_due");
    }
    public SelenideElement selectStartDate() {
        return $("#form-date_started");
    }
    public SelenideElement saveTaskButton() {
        return $("button[type=submit]");
    }
    public SelenideElement cancelTaskButton() {
        return $x("//a[text()='cancel']");
    }
    public SelenideElement selectMonth() {
        return $x("//span[@class='ui-datepicker-month']");
    }
    public SelenideElement selectYear() {
        return $x("//span[@class='ui-datepicker-year']");
    }
    public SelenideElement selectArrow() {
        return $x("//span[@class='ui-icon ui-icon-circle-triangle-e']");
    }
    public ElementsCollection selectDay() {
        return $$x("//a[@class='ui-state-default']");
    }
    public SelenideElement doneBtnCalendar() {
        return $x("//button[text()='Done']");
    }
    public SelenideElement errorMsgTask() {
        return $x("//*[@id='modal-content']/form/div/div[1]/ul/li[text()='The title is required']");
    }
}
