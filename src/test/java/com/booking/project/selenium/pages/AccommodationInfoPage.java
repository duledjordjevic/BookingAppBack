package com.booking.project.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccommodationInfoPage {
    private WebDriver driver;
    public AccommodationInfoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".title>h1")
    private WebElement title;

    @FindBy(css = ".updateButton .mdc-button__label")
    private WebElement update;

    public boolean isPageOpened(String name) {

        return (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.textToBePresentInElement(title, name));
    }

    public void clickUpdate(){
        update.click();
    }
}
