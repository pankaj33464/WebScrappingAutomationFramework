package pageObjectsModels.pageLocators;

import org.openqa.selenium.By;

public class NewsPageLocators {

    public By firstArticleTitle = By.className("fc-item__header");

    public By googleSearchBox = By.name("q");
    public By firstArticleContent = By.className("fc-item__standfirst");

    public By resultItemsFromSearch = By.cssSelector(".tF2Cxc");
}
