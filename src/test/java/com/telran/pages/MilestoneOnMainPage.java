package com.telran.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


/**
 * Created by Zizi, Christina and Mariya on 5/28/2015.
 *
 */
public class MilestoneOnMainPage extends Page {

    @FindBy(xpath = "//div [@class='col-sm-8']/label")
    WebElement categoryDevelopmentalMilestoneTitle;

    @FindBy(xpath = "//div [@class='col-sm-4']/label")
    WebElement categoryTreatmentMilestoneTitle;


    //Category Developmental Milestone buttons
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Language')]")
    WebElement languageButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Movement')]")
    WebElement movementButton;
    @FindBy(xpath = " //div [@class='btn-group']/button[contains(text(),'Eating')]")
    WebElement eatingButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Treatment')]")
    WebElement treatmentButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Other')]")
    WebElement otherButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Toileting')]")
    WebElement toiletingButton;


    //Dropdown list
    @FindBy(xpath = "//*[contains(text(),'Select an Option')]")
    WebElement selectItemList;
    @FindBy(xpath = "//*[contains(text(),'Select an Option')]/../div")
    WebElement selectItemListButton;


    //elements of dropdown list
    //for button Language
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemSmiles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemBabbles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemFirstWord;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemWords;

    //for button Movement

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHoldsHead;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemReachesForObjects;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemCrawls;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemRuns;

    //for  button Eating

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHoldsBottles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemEatsWithSpoon;


    //for button Toileting
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemToiletTrained;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemDressesAlone;

    //button of Treatment
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemSurgery;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemMedicalTrial;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemRemission;

    //Years-Month button
    @FindBy(id = "milestone_years")
    WebElement yearsButton;

    @FindBy(id = "milestone_months")
    WebElement monthButton;


    @FindBy(xpath = "//*[@placeholder='Please Specify Your Milestone']")
    WebElement otherMilestoneFiled;
    @FindBy(xpath = "//*[@placeholder='Tell us more about this milestone']")
    WebElement postField;
    //button submit
    @FindBy(id = "submit")
    WebElement submitButton;

    public MilestoneOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo-env.elasticbeanstalk.com/home";
    }

    // Waits until title of our 'Milestone' Panel appears on the screen
    public void waitUntilMilestonePanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(categoryDevelopmentalMilestoneTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Checks that title of our 'Milestone' Panel have appeared on the screen so we can work with it.
    public boolean isOnMilestonePanel() {
        waitUntilMilestonePanelIsLoaded();
        return exists(categoryDevelopmentalMilestoneTitle);
    }

    public MilestoneOnMainPage clickOnLanguageOption() {
        clickElement(languageButton);
        return this;
    }

    public MilestoneOnMainPage clickOnMovementOption() {
        clickElement(movementButton);
        return this;

    }

    public MilestoneOnMainPage clickOnEatingOption() {
        clickElement(eatingButton);
        return this;


    }

    public MilestoneOnMainPage clickOnToiletingOption() {
        clickElement(toiletingButton);
        return this;
    }

    public MilestoneOnMainPage clickOnOtherOption() {
        clickElement(otherButton);
        return this;
    }

    public MilestoneOnMainPage clickOnTreatmentOption() {
        clickElement(treatmentButton);
        return this;
    }

    public MilestoneOnMainPage clickOnYearsOption() {
        clickElement(yearsButton);
        return this;
    }

    public MilestoneOnMainPage clickOnMonthOption() {
        clickElement(monthButton);
        return this;
    }

    public MilestoneOnMainPage clickOnSubmitOption() {
        clickElement(submitButton);
        return this;
    }

    public MilestoneOnMainPage clickOnItemList() {
        clickElement(selectItemList);
        return this;
    }

    public MilestoneOnMainPage chooseFirstItemFromItemList() {
        clickElement(itemSmiles);
        return this;
    }

    public MilestoneOnMainPage fillTextField(String post) {
        setElementText(postField, post);
        return this;
    }

    public MilestoneOnMainPage sendPost() {
        clickElement(submitButton);
        return this;
    }

}
