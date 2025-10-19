package dev.selenium.pageobjects;

import dev.selenium.utils.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemPage extends BasePage {
    @FindBy(css = "[data-test='back-to-products']")
    BaseElement backButton;
    @FindBy(css = "[data-test='add-to-cart-sauce-labs-backpack']")
    BaseElement addBackpackToCartButton;
    @FindBy(css = "[data-test='remove-sauce-labs-backpack']")
    BaseElement removeBackpackToCartButton;

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItemDisplayed(String itemLabel) {
        try {
            By locator = By.xpath("//*[contains(text(), '" + itemLabel + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ItemPage addBackpackToCart() {
        addBackpackToCartButton.click();

        return this;
    }

    public ItemPage removeBackpackToCart() {
        removeBackpackToCartButton.click();

        return this;
    }
}
