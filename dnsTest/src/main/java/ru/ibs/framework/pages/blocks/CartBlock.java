package ru.ibs.framework.pages.blocks;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.pages.BasePage;
import ru.ibs.framework.pages.CartPage;

public class CartBlock extends BasePage {

    /**
     * Иконка корзины
     */
    @FindBy(xpath = "//span[@class='cart-link__icon']")
    public WebElement cartIcon;

    @FindBy(xpath = "//div[@class = 'buttons']//span[@class='cart-link__price']")
    public WebElement cartPriceIcon;


    /**
     * Тэг цены корзины
     */
    @FindBy(xpath = "//span[@class='cart-link__price']")
    public WebElement cartPriceTag;

    /**
     * кнопка добавления товара карт блока
     */
    @FindBy(xpath = "//div[contains(@class, 'cart-addition_complect')]")
    public WebElement cartBlockAdditionButton;

    public CartPage openCartPage() {
        cartIcon.click();
        return pageManager.getCartPage();
    }


}
