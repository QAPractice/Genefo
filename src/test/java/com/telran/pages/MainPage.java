package com.telran.pages;

/**
 * Created by alex on 28/05/2015.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class MainPage extends Page {

    // Upper Menu buttons
    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Post')]")
    WebElement postButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Medicine')]")
    WebElement medicineButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Milestone')]")
    WebElement milestoneButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Symptoms')]")
    WebElement symptomsButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'What Works')]")
    WebElement whatWorksButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'MD Rating')]")
    WebElement mdRatingButton;

    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Question')]")
    WebElement questionButton;

    //Buttons
    @FindBy(xpath = "//i[@class=\"fa fa-cog fa-2x\"]")
    WebElement cogwheelButton;

    @FindBy(xpath = "//li[ @class=\"ng-scope\"]/*[contains(text(),'My Profiles')]")
    WebElement myProfilesButton;

    @FindBy(xpath="//li[@class='ng-scope']/*[contains(text(),'My Account')]")
    WebElement myAccountButton;

    @FindBy(xpath="//li[@class='ng-scope']/*[contains(text(),'Logout')]")
    WebElement logOutButton;

    @FindBy(xpath = "//ul[@class='people_list people-like-me-list']//li[1]//span[@class='profileName ng-binding']")
    WebElement connectPeopleThisCondition1Button;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//div[@class='post-owner-timestamp-wrapper']//span[@class='profileName post-owner ng-binding']")
    WebElement firstPostNameLink;

    // Upper Tab of sent posts
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]")
    WebElement  UpperSentPostTab;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//div[@class='post-note ng-binding']")
    WebElement  SentPostText;

    @FindBy(xpath = "//div[@class='panel panel-primary']//div[@class='panel-body']//li[1]//span[@class='profileName ng-binding']")
    WebElement  firstFollowed;

    @FindBy(xpath = "//div[@class='col-md-10']/input")
    WebElement  viewConditionFieldForDoctor;

    @FindBy(xpath = "//div[@class='top-row']//a[@class='ng-scope ng-binding']")
    WebElement  dropDownConditionDoctor;

    @FindBy(xpath = "//div[@class='top-row']//button[@class='btn btn-default']")
    WebElement  viewButton;


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }

    public MainPage openMainPage() {
        driver.get(PAGE_URL);
         return this;
    }

    // Waits until title of our 'What works' Panel appears on the screen
    public MainPage waitUntilMainPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(whatWorksButton);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }


    public MainPage openPostPanel() {
        clickElement(postButton);
        return this;
    }

    public MainPage openMedicinePanel() {
        clickElement(medicineButton);
        return this;
    }

    public MainPage openMilestonePanel() {
        clickElement(milestoneButton);
        return this;
    }

    public MainPage openSymptomsPanel() {
        clickElement(symptomsButton);
        return this;
    }

    public MainPage openWhatWorksButtonPanel() {
        clickElement(whatWorksButton);
        return this;
    }

    public MainPage openMDRatingButtonPanel() {
        clickElement(mdRatingButton);
        return this;
    }

    public MainPage openQuestionButtonPanel() {
        clickElement(questionButton);
        return this;
    }

    public boolean isOnMainPage() {
        return exists(milestoneButton);
    }

    public MainPage selectMyProfile () {
        clickElement(cogwheelButton);
        clickElement(myProfilesButton);
        return this;
    }
    public MainPage selectMyAccount () {
        clickElement(cogwheelButton);
        clickElement(myAccountButton);
        return this;
    }

    public void logOut() {
        clickElement(cogwheelButton);
        clickElement(logOutButton);

    }
    //For Following tests
    public void openConnectPeopleThisConditionProfile() {
        clickElement(connectPeopleThisCondition1Button);
    }
    public boolean isFollowingNamePresents(String name) {
        try {
            String[] arrName = name.split(" ");
            driver.findElement(By.xpath("//div[@class=\"panel panel-primary\"]/../div[7]//li[last()]//span[@class=\"profileName ng-binding\"][contains(text()," + arrName[0] + ")]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public MainPage openFollow(){
        clickElement(firstFollowed);
        return this;
    }
    public String getFollowName(){
        return firstFollowed.getText();
    }
    public MainPage openPostNameLink() {
        clickElement(firstPostNameLink);
        return this;
    }
    public void chooseConditionForDoctor(String condition){
        setElementText(viewConditionFieldForDoctor,condition);
    }
    public MainPage chooseConditionFromDropDown(){
        clickElement(dropDownConditionDoctor);
        return this;
    }
    public MainPage clickViewButton(){
        clickElement(viewButton);
        return this;
    }


// Methods for verifying items on sent upper post

    public Boolean verifyTextFromSentPost(String text)  {
        return verifyTextBoolean(SentPostText, text);
    }

}
