package ui.elements.project;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPageElements {
    public SelenideElement actionItem(String item) {
        return $x(String.format("//a[text()[contains(.,'%s')]]", item));
    }
    public SelenideElement projectPageContainer() { return $x("//*[@id='main']/section"); }
    public SelenideElement projectTitle() {
        return $x("//h1//span[@class= 'title']");
    }
    public SelenideElement removeProjectModal() {
        return $("#modal-box");
    }
    public SelenideElement yesButton() {
        return $("#modal-confirm-button");
    }
    public SelenideElement cancelButtonRemoveProject() {
        return $(".form-actions>a");
    }
    public SelenideElement closeRemoveModalButton() {
        return $("#modal-close-button");
    }
}
