package dev.selenium.tests;

import dev.selenium.pageobjects.HomePage;
import dev.selenium.pageobjects.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemOverviewTests extends BaseTest {
    HomePage homePage;

    @BeforeEach
    public void login() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
    }

    @Test
    public void testUserCanOpenAnItem() {
        homePage = new HomePage(driver);


    }

    @Test
    public void testUserCanAddAnItemToCart() {

    }

    @Test
    public void testUserCanRemoveAnItemFromCart() {

    }
}
