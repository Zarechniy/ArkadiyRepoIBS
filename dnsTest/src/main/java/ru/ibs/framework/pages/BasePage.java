package ru.ibs.framework.pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.managers.TestPropManager;
import ru.ibs.framework.pages.blocks.CartBlock;
import ru.ibs.framework.pages.blocks.Header;
import ru.ibs.framework.pages.blocks.SearchInput;

import java.util.List;

public class BasePage {

    protected final DriverManager driverManager = DriverManager.getDriverManager();

    protected final PageManager pageManager = PageManager.getPageManager();

    protected Actions action = new Actions(driverManager.getDriver());

    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);

    private final TestPropManager props = TestPropManager.getTestPropManager();

    public void open(String url) {
        driverManager.getDriver().get(url);
    }

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

//    public WebElement scrollToElementJs(WebElement element) {
//        js.executeScript("arguments[0].scrollIntoView(true);", element);
//        return element;
//    }

    public WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ввод данных в поле
     * @param field - вебэлемент в который будет ввод
     * @param value - значение требуемое для ввода
     */
    public SearchResultPage fillInputField (WebElement field, WebElement field2 , String value) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pageManager.getHomePage().waitUntilElementToBeClickable(field);

        field.click();
        field2.sendKeys(value, Keys.ENTER);
        return pageManager.getSearchResultPage();
    }

    public Header getHeader() {
        return pageManager.getHeader();
    }

    public SearchInput getSearchInput() {
        return pageManager.getSearchInput();
    }

    public CartBlock getCartBlock() {
        return pageManager.getCartBlock();
    }

}
