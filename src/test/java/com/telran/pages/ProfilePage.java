package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Anton on 13-May-15.
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
    @FindBy(xpath = "condition")
    WebElement profileConditionField;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //dropbox

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








}
