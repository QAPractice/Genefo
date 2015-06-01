package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

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
    WebElement physicianFirstNField;

    @FindBy(id = "medical_physician_last")
    WebElement physicianLastNField;


    // text field for posting
    @FindBy(xpath = "//textarea[@name = 'bio']")
    WebElement postField;


   @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[1]//*[@ng-model='medical_rating']")
    WebElement allStarsTogether;

    // Rating star - marked and non-marked together
    @FindBy(xpath = "//div[@class='col-md-7 ng-isolate-scope']//span[@class='ng-isolate-scope ng-valid ng-dirty']/*[3]")
    WebElement thirdRatingStar;

    //Title
    @FindBy(xpath = "//label[@for = 'medical_facility']")
    WebElement medicalFacilityTitle;

    //Buttons
    @FindBy(id = "submit")
    WebElement postButton;

    @FindBy(xpath = "//div[@class='col-md-7 ng-isolate-scope']//span[@class='ng-isolate-scope ng-valid ng-dirty']/*[3][@class='glyphicon ng-scope fa post-fa-star fa-star']")
    WebElement checkedThirdStar;

    @FindBy(xpath = "//div[@class='col-md-7 ng-isolate-scope']//span[@class='ng-isolate-scope ng-valid ng-dirty']/*[3][@class='glyphicon ng-scope fa fa-star-o post-fa-star']")
    WebElement unCheckedThirdStar;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model='medical_rating']")
    WebElement allStarsTogetherInCreatedPost;


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

    public MDRatingOnMainPage fillPhysicianFields(String fNPhysician, String lNPhysician) {
        setElementText(physicianFirstNField, fNPhysician);
        setElementText(physicianLastNField, lNPhysician);
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
    public boolean isThirdStarYellow (){
       return exists(checkedThirdStar);
    }
    public void waitUntilNtwPostCreated() {
        try {
            waitUntilElementIsLoaded();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
