package ui.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

public class AbstractPage {
    public List<Integer> findListIndexesByItem(List<SelenideElement> elementList, String SearchText) {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(SearchText)) {
                ints.add(i);
            }
        }
        return ints;
    }

    public void selectElementFromList(List<SelenideElement> selectElement, String searchText) {
        int index = findListIndexesByItem(selectElement, searchText).get(0);
        selectElement.get(index).click();
    }
}
