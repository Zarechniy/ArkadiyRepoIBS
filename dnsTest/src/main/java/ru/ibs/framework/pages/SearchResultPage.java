package ru.ibs.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends BasePage {

    /**
     * хедер для проверки открытия страницы результатов поиска
     */
    @FindBy(xpath = "//h1[@class='title']")
    private WebElement title;

    @FindBy(xpath = "//a[contains(@class, 'catalog-product__name')]//span")
    private List<WebElement> productList;

    /**
     * Проверка открытия страницы результатов введенного поиска
     *
     * @return - возвращаем обратно на страницу
     */
    public SearchResultPage checkOpenSearchResultPage(String headerTag) {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                headerTag, title.getText());
        return this;
    }

    public ProductCardPage getNeededProduct(String neededProduct) {
        for (WebElement webElement : productList) {
            if (webElement.getText().contains(neededProduct)) {
                webElement.click();
                break;
            }
        }
        return pageManager.getProductCardPage();
    }
}


