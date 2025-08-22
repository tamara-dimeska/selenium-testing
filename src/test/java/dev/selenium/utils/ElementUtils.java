package dev.selenium.utils;

import dev.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementUtils extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(ElementUtils.class);

    public ElementUtils(WebDriver driver) {
        super(driver);
    }

    public static boolean isElementNotDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            log.error("error: ", e);
            return false;
        }
    }

    public static void clickWhenReady(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            log.error("error: ", e);
        }
    }
}
