package dev.selenium.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Screenshot {
    public static void takeScreenshot(WebDriver driver, String name) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name + " - Screenshot", new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            System.err.println("⚠️ Failed to take screenshot: " + e.getMessage());
        }
    }
}
