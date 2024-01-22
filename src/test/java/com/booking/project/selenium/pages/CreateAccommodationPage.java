package com.booking.project.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreateAccommodationPage {
    private WebDriver driver;
    public CreateAccommodationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".main-container .main form .basicInfo .title h1:first-of-type")
    private WebElement title;

    @FindBy(css = "input[formControlName='title']")
    private WebElement propertyName;
    @FindBy(css = "input[formControlName='state']")
    private WebElement propertyState;
    @FindBy(css = "input[formControlName='city']")
    private WebElement propertyCity;
    @FindBy(css = "input[formControlName='postalCode']")
    private WebElement propertyPostalCode;
    @FindBy(css = "input[formControlName='street']")
    private WebElement propertyStreet;
    @FindBy(css = "textarea")
    private WebElement propertyDescription;
    @FindBy(css = "option[value='HOTEL']")
    private WebElement propertyType;
    @FindBy(xpath = "//div[@class='ng-star-inserted'][1]/input[@formcontrolname='cancellationPolicy']")
    private WebElement cancellationPolicy;

    @FindBy(css = "input[formcontrolname='startDate']")
    private WebElement inputStartDate;

    @FindBy(css = "input[formcontrolname='endDate']")
    private WebElement inputEndDate;

    @FindBy(css = "input[formcontrolname='price']")
    private WebElement price;
    @FindBy(css = "#undefined")
    private WebElement pictures;

    @FindBy(css = ".buttonAdd>button")
    private WebElement add;

    @FindBy(css = "#add")
    private WebElement submit;

    @FindBy(id = "min-num-input")
    private WebElement min;

    @FindBy(id = "max-num-input")
    private WebElement max;

    public boolean isPageOpened() {

        return (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.textToBePresentInElement(title, "Tell us a little about your property?"));
    }

    public void setTextToForm(String title, String state, String city, String postalCode, String street, String description){
        propertyName.sendKeys(title);
        propertyState.sendKeys(state);
        propertyCity.sendKeys(city);
        propertyPostalCode.sendKeys(postalCode);
        propertyType.click();

        propertyDescription.sendKeys(description);
        propertyStreet.sendKeys(street);
        min.clear();
        min.sendKeys("2");
        max.clear();
        max.sendKeys("7");
        pictures.sendKeys("C:\\Users\\Stefan\\Documents\\FTN\\Peti semestar\\ISS\\Backend\\src\\test\\resources\\e2e\\picture.jpeg");
        pictures.sendKeys("C:\\Users\\Stefan\\Documents\\FTN\\Peti semestar\\ISS\\Backend\\src\\test\\resources\\e2e\\picture.jpeg");
        pictures.sendKeys("C:\\Users\\Stefan\\Documents\\FTN\\Peti semestar\\ISS\\Backend\\src\\test\\resources\\e2e\\picture.jpeg");
        pictures.sendKeys("C:\\Users\\Stefan\\Documents\\FTN\\Peti semestar\\ISS\\Backend\\src\\test\\resources\\e2e\\picture.jpeg");
        pictures.sendKeys("C:\\Users\\Stefan\\Documents\\FTN\\Peti semestar\\ISS\\Backend\\src\\test\\resources\\e2e\\picture.jpeg");

    }

    public boolean scrollToBottom() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, document.body.scrollHeight-1500)");
        AtomicBoolean status = new AtomicBoolean(false);
        wait.until(webDriver -> {
            Number innerHeight = (Number) js.executeScript("return window.innerHeight;");
            Number scrollY = (Number) js.executeScript("return window.scrollY;");
            Number bodyScrollHeight = (Number) js.executeScript("return document.body.scrollHeight;");

            double innerHeightValue = innerHeight.doubleValue();
            double scrollYValue = scrollY.doubleValue();
            double bodyScrollHeightValue = bodyScrollHeight.doubleValue();

            System.out.println("Checking scroll position - Inner height: " + innerHeightValue + ", Scroll Y: " + scrollYValue + ", Body scroll height: " + bodyScrollHeightValue);

            double targetScrollPosition = bodyScrollHeightValue - innerHeightValue - 1500;
            if (scrollYValue >= targetScrollPosition) {
                status.set(true);
            }else {
                status.set(false);
            }

            return scrollYValue >= targetScrollPosition;
        });
        return status.get();
    }

    public void setDate(String start,String end, String price){

        for(int i = 0; i < 15; i++){
            inputStartDate.sendKeys(Keys.BACK_SPACE);
        }
        inputStartDate.sendKeys(start);
        for(int i = 0; i < 15; i++){
            inputEndDate.sendKeys(Keys.BACK_SPACE);
        }
        inputEndDate.sendKeys(end);
        this.price.clear();
        this.price.sendKeys(price);
        add.click();
    }
    public void submit(){
        submit.click();
    }
}
