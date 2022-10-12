package ru.ibs.framework.tests;

import org.junit.Test;
import ru.ibs.framework.basetests.BaseTests;
import ru.ibs.framework.managers.PageManager;

public class FirstTest extends BaseTests {

    @Test
    public void test() {
        PageManager.getPageManager()
                .getHomePage().closeCityChoiceWidget().getSearchInput()
                .fillInputField(PageManager.getPageManager()
                        .getSearchInput().searchInput, PageManager.getPageManager()
                        .getSearchInput().searchInputAfterClick, "PlayStation 5")
                .checkOpenSearchResultPage("Консоли PlayStation")
                .getNeededProduct("PlayStation 5 Digital Edition")
                .checkIfProductCardPageOpened("PlayStation 5 Digital Edition")
                .getFirstStarterPrice().chooseTwoYearsInsurance().getFirstPriceWithExtendedInsurance().pressBuyButton()
                .getSearchInput().fillInputField(PageManager.getPageManager()
                        .getSearchInput().searchInput, PageManager.getPageManager()
                        .getSearchInput().searchInputAfterClick, "The last of Us")
                .getNeededProduct("The Last of Us:").checkIfProductCardPageOpened("Игра The Last of Us")
                .getSecondStarterPrice().pressBuyButton()
                .checkFirstExpectedAndRealPriceAreEqual()
                .checkIfNeededInsuranceChosen()
                .checkFirstProductPrice()
                .checkSecondProductPrice()
                .checkTotalPrice().deleteSecondProduct()
                .checkIfSecondProductIsDeleted()
                .checkPriceChangedAfterDelete()
                .addProductPressingPlusButton()
                .addProductPressingPlusButton()
                .checkPriceOfThreeProducts()
                .restoreAndCheckDeletedProduct()
                .checkRestoredPrice();
    }
}
