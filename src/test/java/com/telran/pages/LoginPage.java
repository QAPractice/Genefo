package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Л on 5/19/2015.
 */
public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://genefo.com:8080/login";
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(),'Login to')]")
    WebElement loginTitle;

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement loginButton;

    public LoginPage openLoginPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public void waitUntilLoginPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(loginTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnLoginPage() {
        waitUntilLoginPageIsLoaded();
        return exists(loginTitle);
    }

    public LoginPage fillEmailField(String email) {
        setElementText(emailField, email);
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        setElementText(passwordField, password);
        return this;
    }

    public LoginPage clickToLogin() {
        clickElement(loginButton);
        return this;
    }
}
