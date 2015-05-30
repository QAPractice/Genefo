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
    @FindBy(xpath = "//*[@class='ng-click']/*[contains(text(),'button')]")
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
    @FindBy(name = "relationID")
    WebElement profilePatientDropdown;
    @FindBy()
    WebElement profileGenderTooltip;
    @FindBy()
    WebElement profileRaceToltip;
    @FindBy(name = "birthmonth")
    WebElement profileBirthdayToltipMonth;
    @FindBy(name = "birthday")
    WebElement profileBirthdayToltipDay;
    @FindBy(name = "birthyear")
    WebElement profileBirthdayToltipYear;
    @FindBy(xpath = "//*[contains(@id,'typeahead-0LH-9401') and contains(@ng-show, 'isOpen()')]/*[1]")
    WebElement profileLocationToltip;
    @FindBy(xpath = "//*[contains(@id,'typeahead') and contains(@ng-show, 'isOpen()')]/*[1]")
    WebElement conditionTooltip;
    @FindBy(name = "diagnosisYear")
    WebElement profileDiagnoseTooltipYear;

    private String label; // Keeps last label from dropdown list.



    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilProfilePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(profilePatientDropdown);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnProfilePage() {
        waitUntilProfilePageIsLoaded();
        return exists(profilePatientDropdown);
    }

    public ProfilePage selectGender(String value) {
        label = selectValueInDropdown(profileGender, value);
        return this;
    }

    public boolean isGenderSelected(String chosenOption) {
        return verifyTextBooleanInDropDown(label, chosenOption);
    }

    public ProfilePage selectProfilePatient(String value2) {
        selectValueInDropdown(profilePatientDropdown, value2);
        return this;
    }

    public boolean isPatientSelected(String value2) {
        return verifyTextBoolean(profilePatientDropdown, value2);
    }

    public ProfilePage selectMonth(String value) {
        selectValueInDropdown(profileBirthdayToltipMonth, value);
        return this;
    }

    public boolean isMonthSelected(String value) {

        return verifyTextBoolean(profileBirthdayToltipMonth, value);
    }

    public ProfilePage selectDay(String value) {
        selectValueInDropdown(profileBirthdayToltipDay, value);
        return this;
    }

    public boolean isDaySelected(String value) {
        return verifyTextBoolean(profileBirthdayToltipDay, value);
    }

    public ProfilePage selectYear(String value) {
        selectValueInDropdown(profileBirthdayToltipYear, value);
        return this;
    }

    public boolean isYearSelected(String value) {
        return verifyTextBoolean(profileBirthdayToltipYear, value);
    }

    public ProfilePage selectDiagnoseYear(String value) {
        selectValueInDropdown(profileDiagnoseTooltipYear, value);
        return this;
    }

    public boolean isDiagnoseYearSelected(String value) {
        return verifyTextBoolean(profileDiagnoseTooltipYear, value);
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

    public void autoFillCondition() {
        clickElement(conditionTooltip);
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
    public String getProfileName(){
        return profileFirstNameField.getText() + " " + profileLastNameField.getText();
    }


}











