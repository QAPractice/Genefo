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
    //Stars
   @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[1]//*[@ng-model='medical_rating']")
    WebElement allStarsTogether;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[1]//*[@ng-model='medical_rating']/*[3]")
    WebElement thirdRatingStar;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[3][@class='glyphicon ng-scope fa fa-star post-fa-star']")
    WebElement checkedThirdStarInPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[4][@class='glyphicon ng-scope fa fa-star-o post-fa-star']")
    WebElement unCheckedThirdStarInPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model='medicalPro_effect']")
    WebElement allStarsTogetherInCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[1]/td[2]")
    WebElement facilityOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[2]/td[2]")
    WebElement physicianOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-note ng-binding']")
    WebElement textInCreatedPost;
    //Title
    @FindBy(xpath = "//label[@for = 'medical_facility']")
    WebElement medicalFacilityTitle;
    //Buttons
    @FindBy(id = "submit")
    WebElement postButton;

    public MDRatingOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://52.10.6.51:8080/home";
    }
    // Waits until title of our 'MD Rating' Panel appears on the screen
    public void waitUntilMDRatingPanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(medicalFacilityTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Checks that title of our 'MD Rating' Panel have appeared on the screen so we can work with it.
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
        return exists(checkedThirdStarInPost);
    }

    public void waitUntilNewPostisLoaded() {
        try {
            waitUntilElementIsLoaded(allStarsTogetherInCreatedPost);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isThirdStarChecked() {
        waitUntilNewPostisLoaded();
        return exists(checkedThirdStarInPost);
    }
    public boolean isFacilityNameCorrect(String name){
        return verifyTextBoolean(facilityOnNewCreatedPost, name);
    }
    public boolean isPhysicianNameCorrect(String name) {
        return verifyTextBoolean(physicianOnNewCreatedPost, name);
    }
    public boolean isTextCorrect(String name) {
        return verifyTextBoolean(textInCreatedPost, name);
    }

}
