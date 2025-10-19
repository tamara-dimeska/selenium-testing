package dev.selenium.tests;

import dev.selenium.pageobjects.*;
import dev.selenium.utils.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemDetailedTest extends BaseTest {
    HomePage homePage;
    ItemPage itemPage;
    CartNavBar cartNavBar;
    ShoppingCartPage shoppingCartPage;

    @BeforeEach
    public void loginAndOpenItem() {
        User user = new User("standard_user", System.getenv("USER_PASSWORD"));
        homePage = (HomePage) new LoginPage(driver).login(user);
        itemPage = homePage.openItem("Backpack");
    }

    @Test
    public void testUseCanAddAnItemToCart() {
        cartNavBar = new CartNavBar(driver);

        itemPage = itemPage.addBackpackToCart();

        assertTrue(cartNavBar.isTextDisplayedInCartIcon("1"), "There is more/less than one items in cart.");

        shoppingCartPage = cartNavBar.openShoppingCart();

        assertTrue(shoppingCartPage.isTitleDisplayed(), "Shopping cart title is not displayed.");
        assertTrue(shoppingCartPage.isItemDisplayed("Backpack"), "Backpack item is not in cart.");
        assertFalse(shoppingCartPage.isItemDisplayed("Bike Light"), "Bike Light item is displayed.");
    }

    @Test
    public void testUseCanRemoveAnItemFromCart() {
        cartNavBar = new CartNavBar(driver);

        itemPage = itemPage.addBackpackToCart().removeBackpackToCart();

        assertFalse(cartNavBar.isItemsInCartDisplayed(), "Items in cart icon is displayed.");

        shoppingCartPage = cartNavBar.openShoppingCart();
        assertTrue(shoppingCartPage.isTitleDisplayed(), "Shopping cart title is not displayed.");
        assertFalse(shoppingCartPage.isItemDisplayed("Backpack"), "Backpack item is in cart.");
    }
}
