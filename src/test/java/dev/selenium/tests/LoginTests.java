package dev.selenium.tests;

import dev.selenium.pageobjects.HomePage;
import dev.selenium.pageobjects.LoginPage;
import dev.selenium.utils.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @Test
    public void testUserCanLoginAndLogout() {
        loginPage = new LoginPage(driver);
        User user = new User("standard_user", System.getenv("USER_PASSWORD"));
        homePage = (HomePage) loginPage.login(user);

        assertTrue(homePage.isTitleDisplayed(), "Home page title is not displayed. The user is not logged in.");

        loginPage = homePage.logout();

        assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed. The user is not successfully logged out.");
    }

    @Test
    public void testUserCannotLoginWithIncorrectData() {
        User user = new User("standard_user", "111");
        loginPage = (LoginPage) new LoginPage(driver)
                .login(user);

        assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed. The user is logged in.");
        assertTrue(loginPage.isErrorMessageDisplayed(), "No error message is displayed.");
    }

}
