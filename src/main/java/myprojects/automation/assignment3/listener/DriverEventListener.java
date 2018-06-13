package myprojects.automation.assignment3.listener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class DriverEventListener extends AbstractWebDriverEventListener{
    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Try to navigate to: " + s);
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Successfully navigated to: " + s);
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Try to find : " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println(by.toString() + " found successfully");
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Try to click on: " + webElement.toString());

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println(webElement.toString() + " element was clicked");
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println(String.format("Try to pass %s into %s", charSequences, webElement.toString()));
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println(String.format("The %s was passed into %s", charSequences, webElement.toString()));

    }
}
