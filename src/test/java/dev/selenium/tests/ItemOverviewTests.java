package dev.selenium.tests;

import dev.selenium.pageobjects.*;
import dev.selenium.utils.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemOverviewTests extends BaseTest {
    HomePage homePage;
    ItemPage itemPage;
    CartNavBar cartNavBar;
    ShoppingCartPage shoppingCartPage;
    String backpackItem = "Backpack";
    String bikeLightItem = "Bike Light";

    @BeforeEach
    public void login() {
        User user = new User("standard_user", System.getenv("USER_PASSWORD"));
        homePage = (HomePage) new LoginPage(driver).login(user);
    }

    @Test
    public void testUserCanOpenAnItem() {
        itemPage = new ItemPage(driver);

        itemPage = homePage.openItem(backpackItem);

        assertTrue(itemPage.isItemDisplayed(backpackItem), "Backpack item is not opened.");
        assertFalse(itemPage.isItemDisplayed(bikeLightItem), "Bike Light item is displayed.");
    }

    @Test
    public void testUserCanAddAnItemToCart() {
        cartNavBar = new CartNavBar(driver);

        homePage = homePage.addBackpackToCart();

        assertTrue(cartNavBar.isTextDisplayedInCartIcon("1"), "There is more/less than one items in cart.");

        shoppingCartPage = cartNavBar.openShoppingCart();

        assertTrue(shoppingCartPage.isTitleDisplayed(), "Shopping cart title is not displayed.");
        assertTrue(shoppingCartPage.isItemDisplayed(backpackItem), "Backpack item is not in cart.");
        assertFalse(shoppingCartPage.isItemDisplayed(bikeLightItem), "Bike Light item is displayed.");
    }

    @Test
    public void testUserCanRemoveAnItemFromCart() {
        cartNavBar = new CartNavBar(driver);

        homePage = homePage.addBackpackToCart().removeBackpackToCart();

        assertFalse(cartNavBar.isItemsInCartDisplayed(), "Items in cart icon is displayed.");

        shoppingCartPage = cartNavBar.openShoppingCart();
        assertTrue(shoppingCartPage.isTitleDisplayed(), "Shopping cart title is not displayed.");
        assertFalse(shoppingCartPage.isItemDisplayed(backpackItem), "Backpack item is in cart.");
    }
}
