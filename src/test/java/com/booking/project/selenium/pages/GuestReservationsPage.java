package com.booking.project.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GuestReservationsPage {
    private WebDriver driver;
    public GuestReservationsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "h1")
    private WebElement title;

    @FindBy(xpath = "//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='ACCEPTED']/../../td[contains(@class, 'mat-column-cancellationPolicy') and not(contains(text(), 'NON_REFUNDABLE'))]/../td[contains(@class, 'mat-column-select')]")
    private List<WebElement> acceptedReservationsSelectButtons;

    @FindBy(xpath = "//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='CANCELLED']/../..")
    private List<WebElement> cancelledReservations;

    @FindBy(xpath = "//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='ACCEPTED']/../..")
    private List<WebElement> acceptedReservations;

    @FindBy(css = ".buttons .cancelBtn")
    private WebElement cancelBtn;

    public boolean isPageOpened(){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(title));

        return true;
    }

    public boolean cancelAccepted(){
        if(!acceptedReservationsSelectButtons.isEmpty()){
            int accepted = acceptedReservations.size();
            int cancelled = cancelledReservations.size();
            acceptedReservationsSelectButtons.get(0).click();
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(cancelBtn));
            cancelBtn.click();
            accepted -= 1;
            cancelled += 1;
//            driver.navigate().refresh();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tbody//input[contains(@class, 'mdc-checkbox--selected')]"), 0));
            cancelledReservations = driver.findElements(By.xpath("//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='CANCELLED']/../.."));
            acceptedReservations = driver.findElements(By.xpath("//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='ACCEPTED']/../.."));
            if (acceptedReservations.size() == accepted && cancelledReservations.size() == cancelled){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }


}
