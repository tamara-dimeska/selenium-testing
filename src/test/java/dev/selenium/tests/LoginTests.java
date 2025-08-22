package dev.selenium.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests {

    @Test
    public void testUserCanLoginAndLogout() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        String title = driver.getTitle();
        assertEquals("Swag Labs", title, "Unexpected title");
        driver.quit();
    }

    @Test
    public void testUserCannotLoginWithIncorrectData() {

    }

}
