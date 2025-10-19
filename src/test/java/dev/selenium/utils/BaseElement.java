package dev.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseElement {
    protected WebDriver driver;
    protected WebElement element;
    protected WebDriverWait wait;

    public BaseElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void click() {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public boolean isNotDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean isDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTextPresent(String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendKeys(String text) {
        element.sendKeys(text);
    }
}
