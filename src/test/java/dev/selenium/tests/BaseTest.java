package dev.selenium.tests;

import dev.selenium.utils.Screenshot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    WebDriver driver;

    @BeforeEach
    public void launch() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.get("http://localhost:3000");
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (testInfo.getTestMethod().isPresent()) {
            try {
                Screenshot.takeScreenshot(driver, testInfo.getTestMethod().get().getName());
            }
            catch (Exception e) {
                System.out.println("Failed to take screenshot: " + e.getMessage());
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
