package ru.ibs.framework.managers;

import ru.ibs.framework.Product;
import ru.ibs.framework.pages.CartPage;
import ru.ibs.framework.pages.HomePage;
import ru.ibs.framework.pages.ProductCardPage;
import ru.ibs.framework.pages.SearchResultPage;
import ru.ibs.framework.pages.blocks.CartBlock;
import ru.ibs.framework.pages.blocks.Header;
import ru.ibs.framework.pages.blocks.SearchInput;

public class PageManager {

    private static PageManager pageManager;

    private HomePage homePage;

    private CartPage cartPage;

    private SearchResultPage searchResultPage;

    private ProductCardPage productCardPage;

    private Header header;

    private SearchInput searchInput;

    private CartBlock cartBlock;

    private Product product;

    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public  HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public  CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }

    public  ProductCardPage getProductCardPage() {
        if (productCardPage == null) {
            productCardPage = new ProductCardPage();
        }
        return productCardPage;
    }

    public  SearchResultPage getSearchResultPage() {
        if (searchResultPage == null) {
            searchResultPage = new SearchResultPage();
        }
        return searchResultPage;
    }

    public Header getHeader() {
        if (header == null) {
            header = new Header();
        }
        return header;
    }

    public SearchInput getSearchInput() {
        if (searchInput == null) {
            searchInput = new SearchInput();
        }
        return searchInput;
    }

    public CartBlock getCartBlock() {
        if (cartBlock == null) {
            cartBlock = new CartBlock();
        }
        return cartBlock;
    }

    public Product getProduct() {
        if (product == null) {
            product = new Product();
        }
        return product;
    }

}
