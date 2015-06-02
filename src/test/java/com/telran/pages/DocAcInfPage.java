package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
/**
 * Created by Oleg on 31.05.2015.
 */
public class DocAcInfPage extends Page{

    @FindBy(xpath = "//*[contains(text(),'Healthcare Professional Account Information')]")
    WebElement docAcInfTitle;

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "newpassword")
    WebElement passwordField;

    @FindBy(xpath = "//*[@class='account-section ng-scope']/descendant::button[@class='btn btn-primary'and contains(.,'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath ="//*[@id='submit' and @data-target='#loginModal'] ")
    WebElement saveButton;

    @FindBy(xpath ="//*[@id='submit' and @data-target='#loginModal'and @disabled='disabled']")
    WebElement saveDisablelButton;

    @FindBy(xpath = "//*[@class='errormsg']/*[@class='fa fa-times']")
    WebElement emailErrAlert;

    @FindBy(xpath = "//*[@class='errormsg hidden-xs']/*[@class='fa fa-times']")
    WebElement passwordErrAlert;

    public DocAcInfPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/account_hcp/account";
        PageFactory.initElements(driver, this);
    }

    public DocAcInfPage waitUntilDocAcInfPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(docAcInfTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnDocAcInfPage() {
        waitUntilDocAcInfPageIsLoaded();
        return exists(docAcInfTitle);
    }

    public DocAcInfPage fillEmailField(String email) {
        setElementText(emailField, email);
        return this;
    }

    public DocAcInfPage fillPasswordField(String password) {
        setElementText(passwordField, password);
        return this;
    }

    public DocAcInfPage clickOnCancel() {
        clickElement(cancelButton);
        return this;
    }

    public DocAcInfPage clickOnSaveButton() {
        clickElement(saveButton);
        return this;
    }

    public boolean alertMessageInvalidEmail() {
        return exists(emailErrAlert);
    }

    public boolean alertMessageInvalidPassword() {
        return exists(passwordErrAlert);
    }
}
