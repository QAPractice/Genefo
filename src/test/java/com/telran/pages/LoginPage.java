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

    @FindBy(xpath = "//*[contains(text(),'Login to')]")
    WebElement loginTitle;

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement loginButton;

    @FindBy(xpath = "//*[contains(text(),'Sign Up')]")
    WebElement signUpReg;


    public LoginPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/login";
        PageFactory.initElements(driver, this);
    }

    public LoginPage openLoginPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public LoginPage waitUntilLoginPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(loginTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
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

    public LoginPage clickOnSignUpButton() {
        clickElement(signUpReg);
        return this;
    }

    public LoginPage login(String email, String password) {
        openLoginPage();
        waitUntilLoginPageIsLoaded();
        fillEmailField(email);
        fillPasswordField(password);
        clickToLogin();
        return this;
    }
}
