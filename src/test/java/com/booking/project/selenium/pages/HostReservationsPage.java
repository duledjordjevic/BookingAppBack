package com.booking.project.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HostReservationsPage {

    private WebDriver driver;
    public HostReservationsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "h1")
    private WebElement title;

    @FindBy(xpath = "//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='PENDING']/../../td[contains(@class, 'mat-column-select')]")
    private List<WebElement> pendingReservationsSelectButtons;

    @FindBy(css = "#declineBtn")
    private WebElement declineBtn;

    @FindBy(css = "#acceptBtn")
    private WebElement acceptBtn;

    @FindBy(xpath = "//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='DECLINED']/../..")
    private List<WebElement> declinedReservations;

    @FindBy(xpath = "//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='ACCEPTED']/../..")
    private List<WebElement> acceptedReservations;

    @FindBy(xpath = "//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='PENDING']/../..")
    private List<WebElement> pendingReservations;

    public boolean isPageOpened(){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(title));

        return true;
    }


    public boolean acceptOrDeclineReservation(boolean isAccept){
        if(!pendingReservationsSelectButtons.isEmpty()){
            if(isAccept){
                pendingReservationsSelectButtons.get(0).click();
                LocalDate startDate = parse(pendingReservationsSelectButtons.get(0).findElement(By.xpath("../td[contains(@class, 'mat-column-startDate')]")).getText());
                LocalDate endDate = parse(pendingReservationsSelectButtons.get(0).findElement(By.xpath("../td[contains(@class, 'mat-column-endDate')]")).getText());
                int pending = pendingReservations.size();
                int accepted = acceptedReservations.size();
                int overlaps = 0;
                for(WebElement pendingReservation: pendingReservations){
                    LocalDate start = parse(pendingReservation.findElement(By.xpath("td[contains(@class, 'mat-column-startDate')]")).getText());
                    LocalDate end = parse(pendingReservation.findElement(By.xpath("td[contains(@class, 'mat-column-endDate')]")).getText());
                    if(isOverlap(startDate, endDate, start, end)){
                        overlaps += 1;
                    }
                }
                Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(acceptBtn));
                acceptBtn.click();
                accepted += 1;
                pending -= overlaps;
                wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//input[contains(@class, 'mdc-checkbox--selected')]"), 0));
                pendingReservations = driver.findElements(By.xpath("//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='PENDING']/../.."));
                acceptedReservations = driver.findElements(By.xpath("//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='ACCEPTED']/../.."));
                if(accepted == acceptedReservations.size() && pending == pendingReservations.size()){
                    return true;
                }else{
                    return false;
                }

            }else{
                int pending = pendingReservations.size();
                int declined = declinedReservations.size();
                pendingReservationsSelectButtons.get(0).click();
                Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(declineBtn));
                declineBtn.click();
                pending -= 1;
                declined += 1;
//                driver.navigate().refresh();
                wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//input[contains(@class, 'mdc-checkbox--selected')]"), 0));

                pendingReservations = driver.findElements(By.xpath("//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='PENDING']/../.."));
                declinedReservations = driver.findElements(By.xpath("//tbody/tr/td[contains(@class, 'mat-column-status')]/div[text()='DECLINED']/../.."));

                if (pendingReservations.size() == pending && declinedReservations.size() == declined){
                    return true;
                }else{
                    return false;
                }
            }

        }
        return true;
    }

    private LocalDate parse(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // (:startDate <= r.endDate AND :endDate >= r.startDate)
    private boolean isOverlap(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2){
        return (startDate2.isBefore(endDate1) || startDate2.isEqual(endDate1)) && (endDate2.isAfter(startDate1) || endDate2.isEqual(startDate1));
    }
}
