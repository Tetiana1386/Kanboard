package ui.elements.project;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectFormElements {
    public SelenideElement projectNameField() {
        return $("#form-name");
    }
    public SelenideElement projectIdentifierField() { return $("#form-identifier"); }
    public SelenideElement taskLimitField() {
        return $("#form-task_limit");
    }
    public SelenideElement projectDuplicateSelect() {
        return $("#form-src_project_id");
    }
    public SelenideElement saveButton() {
        return $("button[type='submit']");
    }
    public SelenideElement cancelButton() {
        return $x("//div[@class='form-actions']//a[contains(text(),'cancel')]");
    }
    public SelenideElement closeModalButton() { return $("#modal-close-button"); }
    public SelenideElement formError() { return $(".form-errors"); }
}
