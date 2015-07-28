package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Christina on 6/22/15.
 */
public class FiltersOfPatientOnMainPage extends Page {

    //fields of Patient MainPage
    @FindBy(xpath = "//div[@class='condition-column-title condition-column-title-regular col-md-9 ng-binding']")
    WebElement conditionFieldOfPatientOnMainPage;
    @FindBy(xpath = "//div[@class='profile_selector_name ng-binding']")
    WebElement nameFieldOfPatientOnMainPage;
    @FindBy(xpath = "//div[@class='panel-body']/span[1]")
    WebElement displayingMyPosts;
    //Change Filter button
    @FindBy(xpath = "//span[@class='btn-filter btn ng-binding btn-default']")
    WebElement changeFilterButton;
    //Apply Filter button
    @FindBy(xpath = "//span[@class='btn-filter btn ng-binding btn-success']")
    WebElement applyFilterButton;
    //radioButton for change of filter
    @FindBy(xpath = ".//*[@id='post_type_profile']")
    WebElement myPostsOnlyRadioButton;
    @FindBy(xpath = ".//*[@id='post_type_network']")
    WebElement peopleIAmFollowingAndMyPostsOnlyRadioButton;
    @FindBy(xpath = ".//*[@id='post_type_condition']")
    WebElement myConditionAndPeopleIAmFollowingAndMyPostsOnlyRadioButton;
    //names of posted posts  (amount: 7 posts down the list)
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='profileName post-owner ng-binding']")
    WebElement nameOfOwnerFirstPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[6]//*[@class='profileName post-owner ng-binding']")
    WebElement nameOfOwnerSecondPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[7]//*[@class='profileName post-owner ng-binding']")
    WebElement nameOfOwnerThirdPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[8]//*[@class='profileName post-owner ng-binding']")
    WebElement nameOfOwnerFourthPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[9]//*[@class='profileName post-owner ng-binding']")
    WebElement nameOfOwnerFifthPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[10]//*[@class='profileName post-owner ng-binding']")
    WebElement nameOfOwnerSixthPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[11]//*[@class='profileName post-owner ng-binding']")
    WebElement nameOfOwnerSeventhPost;
    //time of posted posts  (amount: 7 posts down the list)
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[6]//*[@class='post-timestamp ng-binding']")
    WebElement timeOfFirstPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[7]//*[@class='post-timestamp ng-binding']")
    WebElement timeOfSecondPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[8]//*[@class='post-timestamp ng-binding']")
    WebElement timeOfThirdPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[9]//*[@class='post-timestamp ng-binding']")
    WebElement timeOfFourthPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[10]//*[@class='post-timestamp ng-binding']")
    WebElement timeOfFifthPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[11]//*[@class='post-timestamp ng-binding']")
    WebElement timeOfSixthPost;
    @FindBy(xpath = ". //*[@class='panel story-panel ng-scope panel-default']/../div[12]//*[@class='post-timestamp ng-binding']")
    WebElement timeOfSeventhPost;

    //constructor
    public FiltersOfPatientOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = baseUrl + "/home";
    }

 /*   //for doctor account

    @FindBy(xpath = "//input[@id='medicine_name']")
    WebElement fieldViewAGeneticConditionByName;
    @FindBy(xpath = "/*//*[@class='col-md-2']/button[1]")
    WebElement viewForFieldViewAGeneticConditionByNameButton;
    @FindBy(xpath = "//span[@class='filter-label ng-binding']")
    WebElement displayingPostsFromEveryoneInMyCondition;*/

    public FiltersOfPatientOnMainPage clickOnChangeFilterButton() {
        clickElement(changeFilterButton);
        return this;
    }

    public FiltersOfPatientOnMainPage clickOnApplyFilterButton() {
        clickElement(applyFilterButton);
        return this;
    }

    public FiltersOfPatientOnMainPage clickOnMyPostsOnlyRadioButton() {
        clickElement(myPostsOnlyRadioButton);
        return this;
    }

    public FiltersOfPatientOnMainPage clickOnPeopleIAmFollowingAndMyPostsOnlyRadioButton() {
        clickElement(peopleIAmFollowingAndMyPostsOnlyRadioButton);
        return this;
    }

    public FiltersOfPatientOnMainPage clickOnMyConditionAndPeopleIAmFollowingAndMyPostsOnlyRadioButton() {
        clickElement(myConditionAndPeopleIAmFollowingAndMyPostsOnlyRadioButton);
        return this;
    }

    public boolean isNameOfOwnerFirstPost(String name){return this.verifyTextBoolean(nameOfOwnerFirstPost,name);}

    public boolean isNameOfOwnerSecondPost(String name){return this.verifyTextBoolean(nameOfOwnerSecondPost,name);}

    public boolean isNameOfOwnerThirdPost(String name){return this.verifyTextBoolean(nameOfOwnerThirdPost,name);}

    public boolean isNameOfOwnerFourthPost(String name){return this.verifyTextBoolean(nameOfOwnerFourthPost,name);}

    public boolean isNameOfOwnerFifthPost(String name){return this.verifyTextBoolean(nameOfOwnerFifthPost,name);}

   /* public boolean isNameOfOwnerSixthPost(String name){return this.verifyTextBoolean(nameOfOwnerSixthPost,name);}

    public boolean isNameOfOwnerSeventhPost(String name){return this.verifyTextBoolean(nameOfOwnerSeventhPost,name);}
*/

/*
    public boolean isTimeOfFirstPost(String name){return this.verifyTextBoolean(timeOfFirstPost,name);}

    public boolean isTimeOSecondPost(String name){return this.verifyTextBoolean(timeOfSecondPost,name);}

    public boolean isTimeOfThirdPost(String name){return this.verifyTextBoolean(timeOfThirdPost,name);}

    public boolean isTimeOfFourthPost(String name){return this.verifyTextBoolean(timeOfFourthPost,name);}

    public boolean isTimeOfFifthPost(String name){return this.verifyTextBoolean(timeOfFifthPost,name);}

    public boolean isTimeOfSixthPost(String name){return this.verifyTextBoolean(timeOfSixthPost,name);}

    public boolean isTimeOfSeventhPost(String name){return this.verifyTextBoolean(timeOfSeventhPost,name);}*/


    //method for waiting Displaying My Posts
    public void waitForDisplayingMyPosts() throws IOException, InterruptedException {
        waitUntilElementIsLoaded(displayingMyPosts);
    }

}
