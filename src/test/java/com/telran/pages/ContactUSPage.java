package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Iakov Volf on 17.06.2015.
 */
public class ContactUSPage extends Page {


    //fields
    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "name")
    WebElement nameField;

    @FindBy(name = "note")
    WebElement messageField;

    @FindBy(xpath = "//*[@id='submit'][@disabled='disabled']")
    WebElement sendButtonDisabled;

    @FindBy(id = "submit")
    WebElement sendButton;

    @FindBy(xpath = "//*[text()='Message has been sent!']")
    WebElement messageSentConfirmation;

    @FindBy(xpath = "//*[text()='Not a valid email']")
    WebElement notValidEmailAlert;


    public ContactUSPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://52.10.6.51:8080/legal/contact";
        PageFactory.initElements(driver, this);
    }

    public ContactUSPage openContactPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public ContactUSPage fillEmailField(String email) {
        setElementText(emailField, email);
        return this;
    }

    public ContactUSPage fillMessageField(String message) {
        setElementText(messageField, message);
        return this;
    }

    public ContactUSPage fillFirstNameField(String firstName) {
        setElementText(nameField, firstName);
        return this;
    }


    public ContactUSPage clickOnSendButton() {
        clickElement(sendButton);
        return this;
    }

    public ContactUSPage clickOnNameField() {
        clickElement(nameField);
        return this;
    }

    public ContactUSPage clickOnMessageField() {
        clickElement(messageField);
        return this;
    }

    public ContactUSPage clickOnEmailField() {
        clickElement(emailField);
        return this;

    }


    public boolean messageSentSuccesifully() {
        return exists(messageSentConfirmation);
    }

    public boolean emailALert() {
        return exists(notValidEmailAlert);
    }

    public boolean isOnSignUpHCPPage() {
        return exists(sendButton);
    }

    public boolean sendButtonDisabled() {
        return exists(sendButtonDisabled);
    }

    public boolean sendButtonEnaabled() {
        return exists(sendButton);
    }


}













