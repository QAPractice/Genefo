package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Л on 5/28/2015.
 */
public class MDRatingOnMainPage extends Page {

    public MDRatingOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }

    //fields
    @FindBy(id = "medical_facility")
    WebElement medicalFacilityField;

    @FindBy(id = "medical_physician_first")
    WebElement physicianField;

    // text field for posting
    @FindBy(xpath = "//textarea[@name = 'bio']")
    WebElement postField;

    // Rating star - marked and non-marked together
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-pristine ng-valid']/*[3]")
    WebElement thirdRatingStar;

    @FindBy(xpath = "//*[@class=\"glyphicon ng-scope fa fa-star post-fa-star\"]")
    WebElement allStarsTogether;

    //Title
    @FindBy(xpath = "//label[@for = \"medical_facility\"]")
    WebElement medicalFacilityTitle;

    //Buttons
    @FindBy(id = "submit")
    WebElement postButton;

    // Waits until title of our 'What works' Panel appears on the screen
    public void waitUntilMDRatingPanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(medicalFacilityTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Checks that title of our 'What works' Panel have appeared on the screen so we can work with it.
    public boolean isOnMDRatingPanel() {
        waitUntilMDRatingPanelIsLoaded();
        return exists(medicalFacilityTitle);
    }

    public MDRatingOnMainPage fillMedicalFacilityField(String medicalFacility) {
        setElementText(medicalFacilityField, medicalFacility);
        return this;
    }

    public MDRatingOnMainPage fillPhysicianField(String physician) {
        setElementText(physicianField, physician);
        return this;
    }

    // We need to click on all stars together to set free each one of them
    public MDRatingOnMainPage clickOnAllStarsTogether() {
        clickElement(allStarsTogether);
        return this;
    }
    // Click on the third star
    public MDRatingOnMainPage rateItThree() {
        clickElement(thirdRatingStar);
        return this;
    }

    public MDRatingOnMainPage fillTextField(String post) {
        setElementText(postField, post);
        return this;
    }

    public MDRatingOnMainPage sendPost() {
        clickElement(postButton);
        return this;
    }

}
