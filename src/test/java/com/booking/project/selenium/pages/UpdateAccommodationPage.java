package com.booking.project.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateAccommodationPage {
    private WebDriver driver;
    public UpdateAccommodationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".title>h1")
    private WebElement title;
    @FindBy(css = "input[formcontrolname='startDate']")
    private WebElement inputStartDate;

    @FindBy(css = "input[formcontrolname='endDate']")
    private WebElement inputEndDate;

    @FindBy(css = "input[formcontrolname='price']")
    private WebElement price;
    @FindBy(css = ".buttonAdd>button")
    private WebElement add;

    @FindBy(css = "#add")
    private WebElement submit;

    @FindBy(css = ".data-table>tbody>tr")
    private List<WebElement> list;

    @FindBy(css = ".buttonDelete")
    private WebElement deleted;

    public boolean isPageOpened() {

        return (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.textToBePresentInElement(title, "Change information of your property"));
    }

    public boolean scrollToBottom() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, document.body.scrollHeight-1200)");
        AtomicBoolean status = new AtomicBoolean(false);
        wait.until(webDriver -> {
            Number innerHeight = (Number) js.executeScript("return window.innerHeight;");
            Number scrollY = (Number) js.executeScript("return window.scrollY;");
            Number bodyScrollHeight = (Number) js.executeScript("return document.body.scrollHeight;");

            double innerHeightValue = innerHeight.doubleValue();
            double scrollYValue = scrollY.doubleValue();
            double bodyScrollHeightValue = bodyScrollHeight.doubleValue();

            System.out.println("Checking scroll position - Inner height: " + innerHeightValue + ", Scroll Y: " + scrollYValue + ", Body scroll height: " + bodyScrollHeightValue);

            double targetScrollPosition = bodyScrollHeightValue - innerHeightValue - 1200;
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

    public boolean checkListSize(int size){
        return  list.size() == size;

    }

    public void deleteDate(int row){
        list.get(row).click();
        deleted.click();
    }

}
