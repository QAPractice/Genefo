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

    public void clickOnLanguageOption() {clickElement(languageButton);}

    public void clickOnMovementOption() {clickElement(movementButton);}

    public void clickOnEatingOption() {clickElement(eatingButton);}

    public void clickOnToiletingOption() {
        clickElement(toiletingButton);
    }

    public void clickOnOtherOption() {
        clickElement(otherButton);
    }

    public void clickOnTreatmentOption() {
        clickElement(treatmentButton);
    }

    public void clickOnYearsOption() {
        clickElement(yearsButton);
    }

    public void clickOnMonthOption() {
        clickElement(monthButton);
    }

    public void clickOnSubmitOption() {
        clickElement(submitButton);
    }


}
