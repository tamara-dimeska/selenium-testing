package dev.selenium.tests;

import dev.selenium.pageobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemOverviewTests extends BaseTest {
    HomePage homePage;
    ItemPage itemPage;
    CartNavBar cartNavBar;
    ShoppingCartPage shoppingCartPage;

    @BeforeEach
    public void login() {
        homePage = (HomePage) new LoginPage(driver).login("standard_user", "secret_sauce");
    }

    @Test
    public void testUserCanOpenAnItem() {
        itemPage = new ItemPage(driver);

        // TODO add backpack and bike light into a const
        itemPage = homePage.openItem("Backpack");

        assertTrue(itemPage.isItemDisplayed("Backpack"), "Backpack item is not opened.");
        assertFalse(itemPage.isItemDisplayed("Bike Light"), "Bike Light item is displayed.");
    }

    @Test
    public void testUserCanAddAnItemToCart() {
        cartNavBar = new CartNavBar(driver);

        homePage = homePage.addBackpackToCart();

        assertTrue(cartNavBar.isTextDisplayedInCartIcon("1"), "There is more/less than one items in cart.");

        shoppingCartPage = cartNavBar.openShoppingCart();

        assertTrue(shoppingCartPage.isTitleDisplayed(), "Shopping cart title is not displayed.");
        assertTrue(shoppingCartPage.isItemDisplayed("Backpack"), "Backpack item is not in cart.");
        assertFalse(shoppingCartPage.isItemDisplayed("Bike Light"), "Bike Light item is displayed.");
    }

    @Test
    public void testUserCanRemoveAnItemFromCart() {
        cartNavBar = new CartNavBar(driver);

        homePage = homePage.addBackpackToCart().removeBackpackToCart();

        assertFalse(cartNavBar.isItemsInCartDisplayed(), "Items in cart icon is displayed.");

        shoppingCartPage = cartNavBar.openShoppingCart();
        assertTrue(shoppingCartPage.isTitleDisplayed(), "Shopping cart title is not displayed.");
        assertFalse(shoppingCartPage.isItemDisplayed("Backpack"), "Backpack item is in cart.");
    }
}
