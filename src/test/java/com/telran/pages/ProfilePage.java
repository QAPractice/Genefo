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
    @FindBy(xpath = "html/body/div[1]/div[1]/div/div[3]/ng-include[2]/div/div[2]/form/div[11]/div/button[1]")
    WebElement cancelButton;

    //fields
    @FindBy(name = "firstName")
    WebElement profileFirstNameField;
    @FindBy(name  = "lastName")
    WebElement profileLastNameField;
    @FindBy(name = "condition")
    WebElement profileConditionField;
    @FindBy(name = "genderID")
    WebElement profileGender;
    @FindBy(name = "bio")
    WebElement profileBioField;
    @FindBy()
    WebElement profilePicture;

    //dropdown
    @FindBy(name = "relationID")
    WebElement profilePatientDropdown;
    @FindBy()
    WebElement profileGenderToltip;
    @FindBy()
    WebElement profileRaceToltip;
    @FindBy()
    WebElement profileBirthdayToltipMonth;
    @FindBy()
    WebElement profileBirthdayToltipDay;
    @FindBy(name="birthmonth")
    WebElement profileBirthdayToltipYear;
    @FindBy(xpath = "//*[contains(@id,'typeahead-0LH-9401') and contains(@ng-show, 'isOpen()')]/*[1]")
    WebElement profileLocationToltip;
    @FindBy(xpath = "//*[@id='typeahead-15Z-4468-option-0']/*/strong")
    WebElement tmp;

    private String label; // Keeps last label from dropdown list.

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
        label = selectValueInDropdown(profileGender, value);
        return this;
    }

    public boolean isGenderSelected(String chosenOption) {

        return verifyTextBooleanInDropDown( label, chosenOption );
    }

    public ProfilePage selectProfilePatient(String value2) {
        selectValueInDropdown(profilePatientDropdown, value2);
        return this;
    }
    public boolean isPatientSelected(String value2){
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

    public ProfilePage autoFillCondition(String condition) {
        clickElement(tmp);
        selectValueInDropdown(profileConditionField, condition);
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
