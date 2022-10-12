package ru.ibs.framework.pages.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.pages.BasePage;
import ru.ibs.framework.pages.HomePage;

public class SearchInput extends BasePage {

//    /**
//     * Фрейм с полем ввода
//     */
//    @FindBy(xpath = "//iframe[contains(@src, 'https://5922505.fls.doubleclick.net/activityi')]")
//    public WebElement searchLineFrame;

    /**
     * Поле ввода поиска(универсальное для всех страниц)
     */
    @FindBy(xpath ="//form[@class='presearch']//input")
    public WebElement searchInput;

    /**
     * Поле ввода поиска после клика(универсальное для всех страниц)
     */
    @FindBy(xpath = "//form[contains(@class, 'presearch_autocomplete')]//input")
    public WebElement searchInputAfterClick;

//    /**
//     * Переключение на фрейм содержащий поле ввода
//     */
//    public HomePage getFrame() {
//        new WebDriverWait(DriverManager.getDriverManager().getDriver(), 3)
//                .until(ExpectedConditions.visibilityOf(searchLineFrame));
//        DriverManager.getDriverManager().getDriver().switchTo().frame(searchLineFrame);
//        return PageManager.getPageManager().getHomePage();
//    }

//    public void closeFrame() {
//        DriverManager.getDriverManager().getDriver().switchTo().defaultContent();
//        PageManager.getPageManager().getHomePage();
//    }

//
//    public void fillInput(String inputText) {
//        driverManager.getDriver().switchTo().defaultContent();
//        searchInput.sendKeys(inputText);
//    }
}
