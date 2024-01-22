package com.booking.project.selenium.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.or;

public class FilterAccommodationsPage {
    private WebDriver driver;
    public FilterAccommodationsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".cards > div:first-child > div > p")
    private WebElement firstAccommodationTitle;
    @FindBy(xpath = "//*[@formControlName='city']")
    private WebElement cityInput;
    @FindBy(xpath = "//*[@formControlName='startDate']")
    private WebElement startDatePicker;
    @FindBy(xpath = "//*[@formControlName='endDate']")
    private WebElement endDatePicker;
    @FindBy(xpath = "//*[@formControlName='numOfGuests']")
    private WebElement  numOfGuestsInput;
    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchButton;
    @FindBy(xpath = "//div[contains(@class,'amenities-price-filter')]/label")
    private List<WebElement> amenitiesLabels;
    @FindBy(xpath = "//*[@role='radiogroup']/*/div/label")
    private List<WebElement> accommodationTypeRadioButtons;
    @FindBy(xpath = "//div[contains(@class,'amenities-price-filter')]/mat-slider")
    private WebElement priceSlider;
    @FindBy(xpath = "//div[contains(@class,'amenities-price-filter')]/mat-slider/input[1]")
    private WebElement minPriceInput;
    @FindBy(xpath = "//div[contains(@class,'amenities-price-filter')]/mat-slider/input[2]")
    private WebElement maxPriceInput;
    @FindBy(css = ".cards > div > div > p")
    private List<WebElement> addressesLabels;
    @FindBy(css = ".cards > div:first-child")
    private WebElement firstCard;
    @FindBy(xpath = "//h3[text()='There are no accommodations matching your search criteria']")
    private WebElement noSearchResultTitle;

    @FindBy(css = "[ng-reflect-router-link='/guest-reservations']")
    private WebElement guestReservations;

    @FindBy(css = "[ng-reflect-router-link='/host-reservations']")
    private WebElement hostReservations;

    public boolean isPageOpened(){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(firstAccommodationTitle));

        return true;
    }

    public void putDataInHeaderFilter(String city, String startDate, String endDate, String numOfGuests){
        cityInput.click();
        cityInput.sendKeys(city);

        startDatePicker.click();
        startDatePicker.sendKeys(startDate);

        endDatePicker.click();
        endDatePicker.sendKeys(endDate);

        numOfGuestsInput.click();
        numOfGuestsInput.sendKeys(numOfGuests);
    }

    public void putDataInSideFilter(List<String> amenities, String accommodationType){
        for(WebElement amenitie: amenitiesLabels){
            if(amenities.contains(amenitie.getText())){
                amenitie.click();
            }
        }

        for(WebElement accommodationTypeLabel: accommodationTypeRadioButtons){
            if(accommodationType.equals(accommodationTypeLabel.getText())){
                accommodationTypeLabel.click();
            }
        }
    }

    public void moveDualSlider(int leftValue, int rightValue) {
        int sliderMaxValue = Integer.parseInt(priceSlider.getAttribute("ng-reflect-max"));
        int sliderMinValue = Integer.parseInt(priceSlider.getAttribute("ng-reflect-min"));

        int leftPrice = leftValue - sliderMinValue;
        int rightPrice = sliderMaxValue - rightValue;

        for(int i = 0; i< leftPrice; i++){
            minPriceInput.sendKeys(Keys.ARROW_RIGHT);
        }
        for(int i = 0; i< rightPrice; i++){
            maxPriceInput.sendKeys(Keys.ARROW_LEFT);
        }

    }
    public void clickSearch(){
        searchButton.click();
    }

    public boolean isSearchResultLoad(){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(or(ExpectedConditions.elementToBeClickable(firstCard),
                ExpectedConditions.textToBePresentInElement(noSearchResultTitle,"There are no accommodations matching your search criteria")));

        return true;
    }
    public boolean checkSearchByAddress(String citySearch) throws InterruptedException {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        for(WebElement addressLabel: addressesLabels){
            String address = addressLabel.getText();
            String city = address.split(",")[0].trim();
            if(!city.equals(citySearch)){
                return false;
            }
        }
        return true;
    }

    public void goToGuestReservations(){
        guestReservations.click();
    }
    public void goToHostReservations(){
        hostReservations.click();
    }

}
