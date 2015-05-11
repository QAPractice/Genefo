package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Iakov Volf on 4/16/2015.
 */
public class LoginPage extends Page {

    //fields
    @FindBy(id = "polelogin" )
    WebElement usernameField;

    @FindBy(id = "password" )
    WebElement passwordField;

    //buttons
    @FindBy(id = "1" )
    WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://ec2-54-166-51-117.compute-1.amazonaws.com:8080/myavailabletime/";
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage() {
        driver.get(PAGE_URL);
    }

    public void fillLoginfields(String userName, String pass) {
        setElementText(usernameField, userName);
        setElementText(passwordField, pass);
    }

    public void waitUntilLoginPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(loginButton);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void login(String login, String pass) throws Exception {
        waitUntilLoginPageIsLoaded();
        fillLoginfields(login, pass);
        clickToLogin();
    }

    public void loginWithoutPass() throws Exception {
        openLoginPage();
        waitUntilElementIsLoaded(loginButton);
        fillLoginfields("Mary", "");
        clickToLogin();
    }

    public void clickToLogin() {
        clickElement(loginButton);
    }


    public boolean exists(WebElement element) {
        return super.exists(element);
    }


    public boolean isLoggedIn(WebElement element) {
        return super.exists(element);
    }

    public boolean isNotLoggedIn() {
        return verifyElementIsPresent(loginButton);
    }

}

