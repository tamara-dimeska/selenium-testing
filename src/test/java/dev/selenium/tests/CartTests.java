package dev.selenium.tests;

import dev.selenium.pageobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTests extends BaseTest {
    HomePage homePage;
    CartNavBar cartNavBar;
    ShoppingCartPage shoppingCartPage;

    @BeforeEach
    public void loginAndOpenShoppingCart() {
        cartNavBar = new CartNavBar(driver);

        homePage = (HomePage) new LoginPage(driver).login("standard_user", "secret_sauce");
        homePage = homePage.addBackpackToCart();
        shoppingCartPage = cartNavBar.openShoppingCart();
    }

    @Test
    public void testUserCanRemoveAnItemFromCart() {
        shoppingCartPage = shoppingCartPage.removeBackpackToCart();

        assertFalse(shoppingCartPage.isItemDisplayed("Backpack"), "Item is still in shopping cart");
    }

    @Test
    public void testUseCanContinueShopping() {
        homePage = shoppingCartPage.clickContinueShopping();

        assertTrue(homePage.isTitleDisplayed(), "User is not on the home page.");
        assertFalse(shoppingCartPage.isTitleDisplayed(), "The user is still on shopping cart page.");

    }
}
