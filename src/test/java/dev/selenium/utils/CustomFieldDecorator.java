package dev.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class CustomFieldDecorator implements FieldDecorator {
    private final WebDriver driver;

    public CustomFieldDecorator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (!field.getType().equals(BaseElement.class)) {
            return null; // only decorate BaseElement fields
        }

        ElementLocator locator = new DefaultElementLocatorFactory(driver).createLocator(field);

        WebElement proxy = (WebElement) Proxy.newProxyInstance(
                loader,
                new Class[]{WebElement.class},
                (proxy1, method, args) -> method.invoke(locator.findElement(), args)
        );

        return new BaseElement(driver, proxy);
    }
}
