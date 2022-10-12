package ru.ibs.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.framework.managers.DriverManager;

import java.util.List;

public class CartPage extends BasePage {

    /**
     * выбранная клавиша расширенной гарантии
     */
    @FindBy(xpath = "//div[contains(@class,'base-ui-radio-button__checked') and contains(.,'24')]")
    private WebElement pointedExtendedInsurance;

    /**
     *
     */
    @FindBy(xpath = "//div[contains(@class,'base-ui-radio-button__checked') and contains(.,'24')]/..//span[contains(@class,'additional-warranties-row__price')]")
    private WebElement extendedInsurancePrice;

    /**
     * цена первого продукта
     */
    @FindBy(xpath = "//div[contains(@class, 'product-set-desktop')]//span[@class = 'price__current']")
    private WebElement firstProductPrice;

    /**
     * цена второго продукта
     */
    @FindBy(xpath = "//div[contains(@class, 'cart-items__product')][3]//span[@class = 'price__current']")
    private WebElement secondProductPrice;

    /**
     * общая цена
     */
    @FindBy(xpath = "//div[contains(@id, 'total-amount')]//span[@class = 'price__current']")
    private WebElement totalPrice;

    /**
     * кнопка удаления второго продукта
     */
    @FindBy(xpath = "//div[contains(@data-cart-product-id, '')][3]//p[contains(.,'Удалить')]")
    private WebElement deleteSecondProductButton;

    /**
     * хедер второго продукта для проверки удаления из корзины
     */
    @FindBy(xpath = "//a[contains(@target, '_self')]")
    private WebElement secondProductHeader;

    /**
     * вернуть удаленные покупки
     */
    @FindBy(xpath = "//span[contains(@class, 'cart-tab-menu__item')]//span[contains(@class,'restore')]")
    private WebElement restoreDeletedProducts;

    /**
     * кнопка добавить первый продукт +1
     */
    @FindBy(xpath = "//i[contains(@class, 'count-buttons__icon-plus')]")
    private WebElement plusProductButton;

    /**
     * проверка гарантии 24 мес
     *
     * @return - возвращаем страничку
     */
    public CartPage checkIfNeededInsuranceChosen() {
        Assert.assertTrue("Выбрана не та гарантия", pointedExtendedInsurance.isDisplayed());
        return this;
    }

    /**
     * проверка цены в корзине и до ее добавления у плойки
     *
     * @return - страница корзина
     */
    public CartPage checkFirstProductPrice() {
        String firstPriceString =
                (firstProductPrice.getText().replaceAll("[^0-9]", ""));
        int insurancePrice = Integer.parseInt
                (extendedInsurancePrice.getText().replaceAll("[^0-9]", ""));
        System.out.println(insurancePrice);
        int firstPrice = Integer.parseInt
                (firstPriceString) + insurancePrice;
        int comparePrice = pageManager.getProductCardPage().modelPriceMap.get("Цена с расширенной гарантией");
        Assert.assertEquals("Цены до и после добавления в корзину не сходятся",
                comparePrice, firstPrice);
        return this;
    }

    /**
     * проверяем цену в корзине и до добавления второго продукта
     *
     * @return - корзину
     */
    public CartPage checkSecondProductPrice() {
        int secondPrice = Integer.parseInt
                (secondProductPrice.getText().replaceAll("[^0-9]", ""));
        int comparePrice = pageManager.getProductCardPage().modelPriceMap.get("Стартовая цена2");
        Assert.assertEquals("Цены до и после добавления в корзину не сходятся",
                comparePrice, secondPrice);
        return this;
    }

    /**
     * проверяем общую цену в корзине и до перехода в корзину
     *
     * @return - корзину
     */
    public CartPage checkTotalPrice() {
        String totalPriceString =
                (totalPrice.getText().replaceAll("[^0-9]", ""));
        int totalPrice = Integer.parseInt(totalPriceString);
        int comparePrice = pageManager.getProductCardPage().modelPriceMap
                .get("Цена с расширенной гарантией")
                + pageManager.getProductCardPage().modelPriceMap.get("Стартовая цена2");

        Assert.assertEquals("Цены до и после добавления в корзину не сходятся",
                comparePrice, totalPrice);
        return this;
    }

    /**
     * удаляем второй товар из корзины
     *
     * @return - корзину
     */
    public CartPage deleteSecondProduct() {
        deleteSecondProductButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * проверка удаления второго продука из корзины
     *
     * @return
     */
    public CartPage checkIfSecondProductIsDeleted() {
        List<WebElement> dynamicElement = DriverManager.getDriverManager().getDriver().findElements(By.id("//a[contains(@target, '_self')]"));
        if (dynamicElement.size() != 0) {
            System.out.println("Второй товар не удален");
        }
        return this;
    }

    /**
     * проверка изменения цены после удаления второго товара
     *
     * @return
     */
    public CartPage checkPriceChangedAfterDelete() {
        String totalPriceString =
                (totalPrice.getText().replaceAll("[^0-9]", ""));
        int totalPrice = Integer.parseInt(totalPriceString);
        int comparePrice = pageManager.getProductCardPage().modelPriceMap
                .get("Цена с расширенной гарантией");

        Assert.assertEquals("Цены до и после добавления в корзину не сходятся",
                totalPrice, comparePrice);
        return this;
    }

    /**
     * добавить первый товар +1
     *
     * @return
     */
    public CartPage addProductPressingPlusButton() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(plusProductButton));
        plusProductButton.click();
        return this;
    }

    /**
     * проверить изменение цены после добавления
     *
     * @return
     */
    public CartPage checkPriceOfThreeProducts() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String totalPriceString =
                (totalPrice.getText().replaceAll("[^0-9]", ""));
        int totalPrice = Integer.parseInt(totalPriceString);
        int comparePrice = (pageManager.getProductCardPage().modelPriceMap
                .get("Цена с расширенной гарантией")) * 3;

        Assert.assertEquals("Цены до и после добавления в корзину не сходятся",
                totalPrice, comparePrice);
        return this;
    }

    /**
     * восстановить удаленные покупки
     *
     * @return
     */
    public CartPage restoreAndCheckDeletedProduct() {
        restoreDeletedProducts.click();
        wait.until(ExpectedConditions.visibilityOf(secondProductHeader));
        Assert.assertTrue("Второй продукт не появился в корзине",
                secondProductHeader.isDisplayed());
        return this;
    }

    /**
     * проверить восстановление удаленных покупок
     *
     * @return
     */
    public CartPage checkRestoredPrice() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String totalPriceString =
                (totalPrice.getText().replaceAll("[^0-9]", ""));
        int totalPrice = Integer.parseInt(totalPriceString);
        int firstPrice = pageManager.getProductCardPage().modelPriceMap
                .get("Цена с расширенной гарантией") * 3;
        int secondPrice = pageManager.getProductCardPage().modelPriceMap.get("Стартовая цена2");
        int comparePrice = firstPrice + secondPrice;

        Assert.assertEquals("Цены до и после добавления в корзину не сходятся",
                totalPrice, comparePrice);
        return this;
    }
}
