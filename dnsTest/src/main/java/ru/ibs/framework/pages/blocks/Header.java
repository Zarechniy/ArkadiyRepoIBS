package ru.ibs.framework.pages.blocks;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.pages.BasePage;
import ru.ibs.framework.pages.HomePage;

import java.util.List;

public class Header extends BasePage {

    /**
     * Хедер меню(универсальный для всех страниц)
     */
    @FindBy(xpath = "//a[@class='header-top-menu__common-link']")
    private List<WebElement> listHeadMenu;

    /**
     * Меню выпадающее из "Покупателям"
     */
    @FindBy(xpath = "//a[contains(@class, 'header-top') and contains(@class, 'link_child')]")
    private List<WebElement> listSubHeadMenu;

    /**
     * Наведение и клик на один из пунктов ХедМеню
     *
     * @param nameHeadMenu - название меню
     * @return HomePage - остаемся на хоумпейдж
     */
    public HomePage selectHeadMenu(String nameHeadMenu) {
        for (WebElement menuItem : listHeadMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameHeadMenu)) {
                waitUntilElementToBeClickable(menuItem).click();
                return pageManager.getHomePage();
            }
        }
        Assert.fail("Меню '" + nameHeadMenu + "' не было найдено на стартовой странице!");
        return pageManager.getHomePage();
    }

}
