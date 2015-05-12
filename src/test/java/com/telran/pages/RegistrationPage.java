package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Iakov Volf on 4/16/2015.
 */
public class RegistrationPage extends Page {

    //fields
    @FindBy(id = "polelogin" )
    WebElement usernameField;

    @FindBy(id = "password" )
    WebElement passwordField;

    //buttons
    @FindBy(id = "1" )
    WebElement loginButton;

    //fields
    @FindBy(xpath = "html//div[1]//form/div[2]//input")
    WebElement emailField;

    @FindBy(xpath = "html//div//div//div//div[2]/div[2]/input")
    WebElement passwordField1;

    @FindBy(xpath = "html//div//div//div//div[3]/div/input")
    WebElement firstNameField;

    @FindBy(xpath = "html//div//div//div//div[4]//input")
    WebElement lastNameField;

    @FindBy(xpath = "html//div//div//div//div[5]//input")
    WebElement conditionField;

    //buttons
    @FindBy(xpath = "html//ng-include//div//ul/li[1]/span/a")
    WebElement signUpInTheUpperRightCorner;

    @FindBy(xpath = ".//*[@id='submit']")
    WebElement signUpButton;

    //checkboxs
    @FindBy(xpath = "html//div//div//div//div[6]//div//input")
    WebElement checkBox18;

    @FindBy(xpath = "html//div//div//div//div[7]//div//input")
    WebElement checkBoxAgree;

    //alerts
    @FindBy(xpath = "html//div//div//div//div/div[3]/div")
    WebElement notaValidEmail;

    @FindBy(xpath = "html//div//div//div//div/div[3]/div")
    WebElement notaValidPassword;

    @FindBy(xpath = "html//div//div//div//div[3]/div/div")
    WebElement notaValidFirstName;

    @FindBy(xpath = "html//div//div//div//div/div[2]/div")
    WebElement notaValidLastName;

    @FindBy(xpath = "html//div//div//div//div/div[2]/div[1]")
    WebElement conditionNotFound;

    @FindBy(xpath = "html//div//div//div//div[6]/div[2]/div[1]")
    WebElement alertToCheckBox18;

    @FindBy(xpath = "html//div//div//div//div[7]/div[3]/div")
    WebElement alertToCheckBoxAgree;


    public RegistrationPage(WebDriver driver) {
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

