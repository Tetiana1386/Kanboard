package ui.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class DashboardElements {
    public SelenideElement dashboardSection() {
        return $("#main");
    }

    public SelenideElement newProjectLink() {
        return $x("//div[@class='page-header']//a[@href='/project/create']");
    }

    public SelenideElement newProjectModal() {
        return $("#modal-content");
    }
}
