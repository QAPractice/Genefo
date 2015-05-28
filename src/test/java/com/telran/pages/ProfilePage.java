package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Anton, Regina, Tanya on 13-May-15.
 */
public class ProfilePage extends Page {
    //Titles
    @FindBy(xpath = "//*[contains(text(),'Create New Profile')]")
    WebElement profileTitle;

    //buttons
    @FindBy(id = "submit")
    WebElement saveProfileButton;
    @FindBy(xpath = "//*[@class=\"ng-click\"]/*[contains(text(),'button')]")
    WebElement cancelButton;

    //fields
    @FindBy(name = "firstName")
    WebElement profileFirstNameField;
    @FindBy(name = "lastName")
    WebElement profileLastNameField;
    @FindBy(name = "condition")
    WebElement profileConditionField;
    @FindBy(name = "genderID")
    WebElement profileGender;

    //dropdown
    @FindBy()
    WebElement profilePatientDropdown;
    @FindBy()
    WebElement profileGenderToltip;
    @FindBy()
    WebElement profileRaceToltip;
    @FindBy()
    WebElement profileBirthdayToltipMonth;
    @FindBy()
    WebElement profileBirthdayToltipDay;
    @FindBy()
    WebElement profileBirthdayToltipYear;
    @FindBy()
    WebElement profileLocationToltip;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilProfilePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(profileTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isOnProfilePage() {
        waitUntilProfilePageIsLoaded();
        return exists(profileTitle);
    }

    public ProfilePage selectGender(String value) {
        selectValueInDropdown(profileGender, value);
        return this;
    }

    public boolean isGenderSelected(String value) {
        return verifyTextBoolean(profileGender, value);
    }

    public ProfilePage selectProfilePatient(String value2) {
        selectValueInDropdown(profilePatientDropdown, value2);
        return this;
    }

    public boolean isPatientSelected(String value2) {
        return verifyTextBoolean(profilePatientDropdown, value2);
    }

    public ProfilePage fillProfileFirstNameField(String firstName) {
        setElementText(profileFirstNameField, firstName);
        return this;
    }

    public ProfilePage fillProfileLastNameField(String lastName) {
        setElementText(profileLastNameField, lastName);
        return this;
    }

    public ProfilePage fillProfileConditionField(String condition) {
        setElementText(profileConditionField, condition);
        clickElement(profileConditionField);
        return this;
    }

    public ProfilePage waitUntilRegProfPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(profileConditionField);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void clickToSubmit() {
        clickElement(saveProfileButton);
        ProfilePage profilePage;
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
    }


}
