package ru.ibs.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class ProductCardPage extends BasePage {

    public HashMap<String, Integer> modelPriceMap = new HashMap<>();

    /**
     * хедер на страничке карты продукта
     */
    @FindBy(xpath = "//h1[contains(@class, 'top__title')]")
    private WebElement productCardPageHeader;

    /**
     * Цена за товар в блоке товара(НЕ КОРЗИНА!!!)
     */
    @FindBy(xpath = "//div[contains(@class, 'product-card-top__buy')]/div/div/div[contains(@class, 'price')]")
    private WebElement productPrice;

    /**
     * кнопка купить
     */
    @FindBy(xpath = "//div[contains(@class, 'product-card-top__buy')]//button[2]")
    private WebElement buyButton;

    /**
     * Кнопка выбора опций гарантии
     */
    @FindBy(xpath = "//div[contains(@class, 'additional-sales') and contains(text(), 'Гарантия')]")
    private WebElement insuranceButton;

    /**
     * кнопка выбора 24 месяцев гарантии
     */
    @FindBy(xpath = "//span[contains(@class, 'product-warranty__period') and contains(text(), '24')]/..")
    private WebElement neededInsurance;

    /**
     *
     * @param neededProduct - название продукта, страница которого нужна
     *                      (можно взять из запроса в поисковой строке)
     * @return - возвращаем страницу
     */
    public ProductCardPage checkIfProductCardPageOpened(String neededProduct) {
        Assert.assertTrue("Открыта страница не с тем товаром",
                productCardPageHeader.getText().contains(neededProduct));
        return this;
    }

    /**
     * цена при переходе на страницу товара
     * @return - цена товара начальная int
     */
    public ProductCardPage getFirstStarterPrice() {
        String starterPriceString = productPrice.getText().replaceAll(" ₽", "");
        String starterPriceStringWithoutText = starterPriceString.replaceAll(" ", "");
        int starterPrice = Integer.parseInt(starterPriceStringWithoutText);
        modelPriceMap.put("Стартовая цена1", starterPrice);
        return this;
    }

    /**
     * Выбор гарантии 24 месяца
     * @return - возвращаем страницу продуктовой карты
     */
    public ProductCardPage chooseTwoYearsInsurance() {
        Assert.assertTrue("Кнопка выбора гарантии не отображается", insuranceButton.isDisplayed());
        insuranceButton.click();
        wait.until(ExpectedConditions.visibilityOf(neededInsurance));
        Assert.assertTrue("Кнопка выбора 24 мес гарантии не отображается",
                neededInsurance.isDisplayed());
        neededInsurance.click();
        return this;
    }

    /**
     * цена измененная
     * @return цена измененная с расширенной гарантией int
     */
    public ProductCardPage getFirstPriceWithExtendedInsurance() {
        String priceString = productPrice.getText().replaceAll(" ₽", "");
        String priceStringWithoutText = priceString.replaceAll(" ", "");
        int priceWithExtendedInsurance = Integer.parseInt(priceStringWithoutText);
        int comparePrices =  modelPriceMap.get("Стартовая цена1");
        Assert.assertNotEquals("Цены с гарантией и без совпадают",
                priceWithExtendedInsurance, comparePrices);
        modelPriceMap.put("Цена с расширенной гарантией", priceWithExtendedInsurance);
        return this;
    }

    /**
     * цена при переходе на страницу товара номер 2
     * @return - цена товара начальная int
     */
    public ProductCardPage getSecondStarterPrice() {
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        String starterPriceString1 = productPrice.getText().replaceAll("[^0-9]", "");
        int starterPrice1 = Integer.parseInt(starterPriceString1);
        modelPriceMap.put("Стартовая цена2", starterPrice1);
        return this;
    }

    /**
     * Нажатие кнопки купть(добавляем в корзину)
     * @return - страница продуктовой карты
     */
    public ProductCardPage pressBuyButton() {
        Assert.assertTrue("Не отображается кнопка купить",
                buyButton.isDisplayed());
        buyButton.click();
        return this;
    }

    /**
     * Проверка соответсвия цены покупак и цены в корзине
     * @return - возвращаем страницу корзины
     */
    public CartPage checkFirstExpectedAndRealPriceAreEqual() {

        int comparePrice = (modelPriceMap
                .get("Цена с расширенной гарантией") + modelPriceMap.get("Стартовая цена2"));

        wait.until(ExpectedConditions.visibilityOf(pageManager.getCartBlock().cartBlockAdditionButton));

        String cartPriceString =
                pageManager.getCartBlock().cartPriceIcon.getText().replaceAll("[^0-9]", "");

        int cartPrice = Integer.parseInt(cartPriceString);

        Assert.assertEquals("Цена в корзине и суммарная цена покупок не сходится", comparePrice, cartPrice);

        Assert.assertTrue("Не отображается иконка корзины",
                pageManager.getCartBlock().cartIcon.isDisplayed());

        pageManager.getCartBlock().cartIcon.click();

        return pageManager.getCartPage();
    }

}
