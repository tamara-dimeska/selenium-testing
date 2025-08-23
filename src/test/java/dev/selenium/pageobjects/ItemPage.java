package dev.selenium.pageobjects;

import dev.selenium.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemPage extends BasePage {
    @FindBy(css = "[data-test='back-to-products']")
    WebElement backButton;
    @FindBy(css = "[data-test='add-to-cart-sauce-labs-backpack']")
    WebElement addBackpackToCartButton;
    @FindBy(css = "[data-test='remove-sauce-labs-backpack']")
    WebElement removeBackpackToCartButton;

    public ItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
        ElementUtils.clickWhenReady(addBackpackToCartButton);

        return this;
    }

    public ItemPage removeBackpackToCart() {
        ElementUtils.clickWhenReady(removeBackpackToCartButton);

        return this;
    }
}
