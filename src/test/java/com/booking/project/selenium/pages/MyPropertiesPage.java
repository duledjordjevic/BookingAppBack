package com.booking.project.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyPropertiesPage {
    private WebDriver driver;
    public MyPropertiesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "h1")
    private WebElement title;
    public boolean isPageOpened() {

        return (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.textToBePresentInElement(title, "My properties"));
    }

    public boolean scrollToTop() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, 0)");
        AtomicBoolean status = new AtomicBoolean(false);
        wait.until(webDriver -> {
            Number scrollY = (Number) js.executeScript("return window.scrollY;");

            double scrollYValue = scrollY.doubleValue();

            System.out.println("Checking scroll position - Scroll Y: " + scrollYValue);

            double margin = 10.0;
            if (scrollYValue <= 0 + margin) {
                status.set(true);
            }else {
                status.set(false);
            }

            return scrollYValue <= 0 + margin;
        });
        return status.get();
    }

    public void clickCard(String name){
        String path = "//h5[text()='"+name+"']";
        WebElement webElement = driver.findElement(By.xpath(path));
        webElement.click();
    }
}
