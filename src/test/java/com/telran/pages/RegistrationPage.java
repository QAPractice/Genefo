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
    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField1;

    @FindBy(name = "firstName")
    WebElement firstNameField;

    @FindBy(name  = "lastName")
    WebElement lastNameField;

    @FindBy(xpath = "condition")
    WebElement conditionField;

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
        this.PAGE_URL = "http://genefo.com:8080/signup_regular";
        PageFactory.initElements(driver, this);
    }

    public void openRegistrationPage() {
        driver.get(PAGE_URL);
    }

    public void fillEmailField(String email){
        setElementText(emailField, email);
    }
    
    public void fillPasswordField(String password){
        setElementText(passwordField, password);
    }
    
    public void fiilFirstNameField(String firstName){
        setElementText(firstNameField, firstName);
    }
    
    public void fillLastNameField(String lastName){
        setElementText(lastNameField, lastName);
    }
    
    public void fillConditionField(String condition){
        setElementText(conditionField, condition);
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

    public void clickToSignUp() {
        clickElement(signUpReg);
    }
    
    public void clickToSubmit(){
        clickElement(submitButton);
    }
    
    public void clickToCheckBox18(){
        clickElement(checkBox18);
    }
    
    public void clickToCheckBoxAgree(){
        clickElement(checkBoxAgree);
    }
}

