package com.booking.project.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    private static String PAGE_URL = "http://localhost:4200/login";

    @FindBy(xpath = "//*[@formControlName='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@formControlName='password']")
    private WebElement passwordInput;

    @FindBy(css = "#button-login")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void login(String email,String password){
        emailInput.click();
        emailInput.sendKeys(email);
        passwordInput.click();
        passwordInput.sendKeys(password);
        loginBtn.click();
    }
}
