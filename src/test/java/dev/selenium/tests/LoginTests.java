package dev.selenium.tests;

import dev.selenium.pageobjects.HomePage;
import dev.selenium.pageobjects.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @Test
    public void testUserCanLoginAndLogout() {
        loginPage = new LoginPage(driver);
        // TODO create user class/interface
        homePage = (HomePage) loginPage.login("standard_user", "secret_sauce");

        assertTrue(homePage.isTitleDisplayed(), "Home page title is not displayed. The user is not logged in.");

        loginPage = homePage.logout();

        assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed. The user is not successfully logged out.");
    }

    @Test
    public void testUserCannotLoginWithIncorrectData() {
        loginPage = (LoginPage) new LoginPage(driver)
                .login("standard_user", "111");

        assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed. The user is logged in.");
        assertTrue(loginPage.isErrorMessageDisplayed(), "No error message is displayed.");
    }

}
