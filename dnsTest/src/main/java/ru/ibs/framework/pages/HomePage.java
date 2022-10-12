package ru.ibs.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    /**
     * Кликабельный хедер лого(универсальный)
     */
    @FindBy(xpath = "//a[@id='header-logo']")
    private WebElement headLogo;

    /**
     * Кнопка дефолтного выбора города
     */
    @FindBy(xpath = "//button[contains(@class,'confirm-city')]")
    private WebElement cityChoiceButton;

    public HomePage() {
    }

    public WebElement getHeadLogo() {
        return headLogo;
    }

    public WebElement getCityChoiceButton() {
        return cityChoiceButton;
    }

    public HomePage closeCityChoiceWidget() {
        Assert.assertTrue("Не появилось окно выбора города", cityChoiceButton.isDisplayed());
        cityChoiceButton.click();
        return this;
    }
}
