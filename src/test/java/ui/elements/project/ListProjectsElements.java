package ui.elements.project;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class ListProjectsElements {
    public SelenideElement getProjectsPage() {
        return $x("//section");
    }
}
