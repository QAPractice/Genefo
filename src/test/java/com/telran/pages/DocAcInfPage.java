package com.telran.pages;

import com.telran.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import java.io.IOException;
/**
 * Created by Oleg on 31.05.2015.
 */
public class DocAcInfPage extends Page{
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(xpath = "//*[contains(text(),'Healthcare Professional Account Information')]")
    @CacheLookup
    WebElement docAcInfTitle;

    @FindBy(name = "email")
    @CacheLookup
    WebElement emailField;

    @FindBy(name = "newpassword")
    @CacheLookup
    WebElement passwordField;

    @FindBy(xpath = "//*[@class='account-section ng-scope']/descendant::button[@class='btn btn-primary'and contains(.,'Cancel')]")
    @CacheLookup
    WebElement cancelButton;

    @FindBy(xpath = "//*[@id='submit' and @data-toggle='modal']")
    @CacheLookup
    WebElement saveButton;

//    @FindBy(xpath = "//*[@id='submit' and @data-target='#loginModal' and @disabled='disabled']")
//    WebElement saveDisablelButton;

    @FindBy(xpath = "//*[@class='errormsg']/*[@class='fa fa-times']")
    @CacheLookup
    WebElement emailErrAlert;

    @FindBy(xpath = "//*[@class='errormsg hidden-xs']/*[@class='fa fa-times']")
    @CacheLookup
    WebElement passwordErrAlert;

    @FindBy(xpath = "//*[@class = 'ng-binding' and contains (text(),'Account Login Information Updated')]")
    @CacheLookup
    WebElement accountSuccess;

    @FindBy(xpath = "//*[@id='myModalLabel']")
    @CacheLookup
    WebElement enterYourCurrentPassTitle;

    @FindBy(xpath = "//*[@id='loginModal']//input")
    @CacheLookup
    WebElement curPasswordField;

    @FindBy(xpath = "//*[@id='loginModal']//button[2]")
    @CacheLookup
    WebElement curSaveButton;

    public DocAcInfPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
        this.PAGE_URL = baseUrl + "/account_hcp/account";
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

    public DocAcInfPage waitUntilEnterYourCurrentPassIsLoaded() {
        try {
            waitUntilElementIsLoaded(enterYourCurrentPassTitle);
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
        Log.info("entering email: " + email + " ");
        return this;
    }

    public DocAcInfPage fillPasswordField(String password) {
        setElementText(passwordField, password);
        Log.info("entering password: " + password + " ");
        new Actions(driver).moveToElement(saveButton).perform();
        return this;
    }

    public DocAcInfPage fillCurrentPasswordField(String password) {
        setElementText(curPasswordField, password);
        Log.info("entering current password: " + password + " ");
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

    public DocAcInfPage clickOnTitle() {
        clickElement(docAcInfTitle);
        return this;
    }

    public DocAcInfPage clickOnCurSaveButton() {
        clickElement(curSaveButton);
        return this;
    }

    public boolean alertMessageInvalidEmail() {
        return exists(emailErrAlert);
    }

    public boolean alertMessageInvalidPassword() {

        return exists(passwordErrAlert);
    }

    public boolean alertMessageAccountSuccess() {
        waitUntilIsLoadedCustomTime(accountSuccess, 5);
        return exists(accountSuccess);
    }

}
