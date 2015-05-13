package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Iakov Volf, Maria on 4/16/2015.
 */
public class RegistrationPage extends Page {

    //fields
    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(name = "firstName")
    WebElement firstNameField;

    @FindBy(name = "lastName")
    WebElement lastNameField;

    @FindBy(name = "condition")
    WebElement conditionField;

    @FindBy(xpath = "//*[@id='typeahead-00A-1397-option-0']")
    WebElement conditionToltip;

    //buttons
    @FindBy(xpath = "html//ng-include//div//ul/li[1]/span/a")
    WebElement signUpReg;

    @FindBy(xpath = ".//*[@id='submit']")
    WebElement submitButton;

    //checkboxs
    @FindBy(name = "isOver18")
    WebElement checkBox18;

    @FindBy(name = "TOS")
    WebElement checkBoxAgree;

    //alerts
    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'email')]")
    WebElement notaValidEmail;

    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'password')]")
    WebElement notaValidPassword;

    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'first name')]")
    WebElement notaValidFirstName;

    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'last name')]")
    WebElement notaValidLastName;

    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'condition')]")
    WebElement conditionNotFound;

    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'18 or older')]")
    WebElement alertToCheckBox18;

    @FindBy(xpath = "//*[@class='col-sm-4 col-xs-12' and contains(.,'Terms')]")
    WebElement alertToCheckBoxAgree;


    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://genefo.com:8080/signup_regular";
        PageFactory.initElements(driver, this);
    }

    public RegistrationPage openRegistrationPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public RegistrationPage fillEmailField(String email) {
        setElementText(emailField, email);
        return this;
    }

    public RegistrationPage fillPasswordField(String password) {
        setElementText(passwordField, password);
        return this;
    }

    public RegistrationPage fillFirstNameField(String firstName) {
        setElementText(firstNameField, firstName);
        return this;
    }

    public RegistrationPage fillLastNameField(String lastName) {
        setElementText(lastNameField, lastName);
        return this;
    }

    public RegistrationPage fillConditionField(String condition) {
        setElementText(conditionField, condition);
        clickElement(conditionToltip);
        return this;
    }

    public RegistrationPage waitUntilRegPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(conditionField);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public RegistrationPage clickToSignUp() {
        clickElement(signUpReg);
        return this;
    }

    public void clickToSubmit() {
        clickElement(submitButton);
        ProfilePage profilePage;
        profilePage = PageFactory.initElements(driver, ProfilePage.class);

    }

    public RegistrationPage clickToCheckBox18() {
        clickElement(checkBox18);
        return this;
    }

    public RegistrationPage clickToCheckBoxAgree() {
        clickElement(checkBoxAgree);
        return this;
    }


    public boolean isOnRegistrationPage() {
        return exists(alertToCheckBox18);

    }
}