package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
/**
 * Created by Oleg on 02.06.2015.
 */
public class DocBasInfPage extends Page{

    //Titles
    @FindBy(xpath = "//*[contains(text(),'Healthcare Professional Basic Information')]")
    WebElement basInfTitle;

    //buttons
    @FindBy(xpath ="//*[@id='submit' and @disabled='disabled']" )
    WebElement saveButton;
    @FindBy(xpath = "//*[@class='profile-info-section ng-scope']/descendant::button[@class='btn btn-primary'and contains(.,'Cancel')]")
    WebElement cancelButton;
    @FindBy (xpath = "//*[@style='overflow: hidden;']")
    WebElement addProfilePicture;

    //fields
    @FindBy(name = "firstName")
    WebElement firstNameField;
    @FindBy(name = "lastName")
    WebElement lastNameField;

    //dropdown
    @FindBy(name = "birthmonth")
    WebElement birthmonthToltipMonth;
    @FindBy(name = "birthday")
    WebElement birthdayToltipDay;
    @FindBy(name = "birthyear")
    WebElement birthyearToltipYear;
    @FindBy(xpath = "//*[@class='form-control ng-dirty ng-invalid ng-invalid-editable error']")
    WebElement locationToltip;

    //Allerts
    @FindBy(xpath = "//*[contains(@ng-show,'firstName')]/*[@class='fa fa-times']")
    WebElement fNameErrAlert;
    @FindBy(xpath = "//*[contains(@ng-show,'lastName')]/*[@class='fa fa-times']")
    WebElement lNameErrAlert;
    @FindBy(xpath = "//*[contains(@ng-show,'location')]/*[@class='fa fa-times']")
    WebElement locationErrAlert;


    public DocBasInfPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/account_hcp/basic";
        PageFactory.initElements(driver, this);
    }

    public DocBasInfPage waitUntilDocAcInfPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(basInfTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnDocBasInfPage() {
        waitUntilDocAcInfPageIsLoaded();
        return exists(basInfTitle);
    }

    public DocBasInfPage fillFirstNameField(String email) {
        setElementText(firstNameField, email);
        return this;
    }

    public DocBasInfPage fillLastNameField(String password) {
        setElementText(lastNameField, password);
        return this;
    }

    public DocBasInfPage clickOnCancel() {
        clickElement(cancelButton);
        return this;
    }

    public DocBasInfPage clickOnSaveButton() {
        clickElement(saveButton);
        return this;
    }

    public boolean alertMessageInvalidFirstName() {

        return exists(fNameErrAlert);
    }

    public boolean alertMessageInvalidLastName() {

        return exists(lNameErrAlert);
    }

    public boolean alertMessageInvalidLocation() {

        return exists(locationErrAlert);
    }
}
