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


    // Upper Tab of sent posts
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]")
    WebElement  UpperSentPostTab;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//div[@class='post-note ng-binding']")
    WebElement  SentPostText;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[contains(text(),'Category')]/../*[contains(text(),'Therapy')]")
    WebElement  SentPostCategoryTherapy;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"what_works_rating\"]/i[3]/span[contains(text(),'*')]")
    WebElement  filledThirdStarInSentPost;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"what_works_rating\"]/i[4]/span[not(contains(text(),'*'))]")
    WebElement nonFilledFourthStarInSentPost;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//td[@class=\"ng-binding\"][contains(text(),'Physical therapy')]")
    WebElement itemPhysicalTherapyInSentPost;


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

    public MainPage selectLogOut () {
        clickElement(cogwheelButton);
        clickElement(logOutButton);
        return this;
    }
    //For Following tests
    public void openConnectPeopleThisConditionProfile() {
        clickElement(connectPeopleThisCondition1Button);
    }
    public boolean isFollowingNamePresents(String name) {
        try {
            driver.findElement(By.xpath(""));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


// Methods for verifying items on sent upper post

    public Boolean verifyTextFromSentPost(String text)  {
        return verifyTextBoolean(SentPostText, text);
    }

    public Boolean verifyCategoryTherapyExistsInSentPost()  {
        return exists(SentPostCategoryTherapy);
    }

    public Boolean verifyThirdStarCheckedInSentPost()  {
        return exists(filledThirdStarInSentPost);
    }

    public Boolean verifyFourthStarNonCheckedInSentPost()  {
        return exists(nonFilledFourthStarInSentPost);
    }

    public Boolean verifyPhysicalTherapyItemExistsInSentPost()  {
        return exists(itemPhysicalTherapyInSentPost);
    }

}
