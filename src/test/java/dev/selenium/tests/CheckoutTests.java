package dev.selenium.tests;

import dev.selenium.pageobjects.*;
import dev.selenium.utils.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTests extends BaseTest{
    HomePage homePage;
    CartNavBar cartNavBar;
    ShoppingCartPage shoppingCartPage;
    CheckoutPage checkoutPage;

    @BeforeEach
    public void loginAndOpenShoppingCart() {
        cartNavBar = new CartNavBar(driver);
        User user = new User("standard_user", System.getenv("USER_PASSWORD"));

        homePage = (HomePage) new LoginPage(driver).login(user);
        homePage = homePage.addBackpackToCart();
        checkoutPage = cartNavBar.openShoppingCart().openCheckout();
    }

    @Test
    public void testUseCanCheckoutWhenTheFormIsFilled() {
        checkoutPage = checkoutPage
                .fillInForm("Test Name", "Test Last name", "12345")
                .clickContinueButton()
                .clickFinishButton();

        assertTrue(checkoutPage.isTitleDisplayed(), "The user is not on the checkout page.");

        checkoutPage = checkoutPage.clickBackButton();

        assertTrue(homePage.isTitleDisplayed(), "The user is not on the home page.");
    }

    @Test
    public void testUseCannotCheckoutWhenTheFormIsNotFilled() {
        checkoutPage = checkoutPage
                .fillInLastName("Last Name")
                .fillInPostCode("12345")
                .clickContinueButton();

        assertTrue(checkoutPage.isErrorMessageDisplayed("Error: First Name is required"), "Error message is not displayed.");
    }

    @Test
    public void testUserCanCancelCheckoutFlow() {
        shoppingCartPage = checkoutPage.clickCancelButton();

        assertTrue(shoppingCartPage.isTitleDisplayed(), "The user is not on shopping cart page.");
    }
}
