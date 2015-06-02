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
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Toileting')]")
    WebElement toiletingButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Other')]")
    WebElement otherButton;

    //Dropdown list
    @FindBy(xpath = "//*[contains(text(),'Select a Language Milestone')]")
    WebElement selectItemList;
    // @FindBy(xpath = "//*[contains(text(),'Select a Language Milestone')]/../div/b")    //?
    // WebElement selectItemListButton;
    @FindBy(xpath = "//*[@class ='chosen-single']/div/b")
    WebElement selectItemListButton;

    @FindBy(xpath = "//*[contains(text(),'Select a Movement Milestone')]")
    WebElement selectItemMovementList;
    @FindBy(xpath = "//*[contains(text(),'Select a Movement Milestone')]/../div/b")
    WebElement selectItemMovementButton;

    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectEatingItemList;
    @FindBy(xpath = "//*[@class='chosen-single']/span/../div/b")
    WebElement selectEatingItemButton;

    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectToiletingItemList;
    @FindBy(xpath = "//*[@class='chosen-single']//div/b")
    WebElement selectToiletingItemListButton;

    @FindBy(xpath = "//*[@class='ng-binding'][contains(text(),'Select a Treatment Milestone')]")
    WebElement selectTreatmentItemList;
    @FindBy(xpath = "//*[@class='chosen-single']//div/b")
    WebElement selectTreatmentButton;

    @FindBy(xpath = "//*[@placeholder='Please Specify Your Milestone']")
    WebElement fillOtherItemList;

    //elements of dropdown list
    //for button Language
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemSmiles;
    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectedSmiles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemBabbles;
    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectedBabbles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemFirstWord;
    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectedFirsWord;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemWords;
    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectedWords;

    //for button Movement
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHoldsHead;
    // @FindBy(xpath = "//*[@class='chosen-single']/span[contains(text(),'Holds head')]")
    // WebElement ItemHoldsHead;
    @FindBy(xpath = " //*[@class='chosen-single']/span")
    WebElement selectedHoldsHead;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemReachesForObjects;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemRollsOver;
    @FindBy(xpath = "//*[@class='chosen-single']/span")        //?
            WebElement selectedReachesForObjects;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemCrawls;
    @FindBy(xpath = "//*[@class='chosen-single']/span")         //?
            WebElement selectedCraws;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemRuns;
    @FindBy(xpath = "//*[@class='chosen-single']/span")        //?
            WebElement selectedRuns;
    //for  button Eating
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHoldsBottles;
    @FindBy(xpath = "//*[@class='chosen-single']/span")        //?
            WebElement selectHoldsBottles;
    /*@FindBy(xpath = " //*[@class='chosen-single']/span")      //?
    WebElement itemEatsWithSpoon;*/
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemEatsWithSpoon;
    @FindBy(xpath = " //*[@class='chosen-single']/span")         //?
            WebElement selectEatsWithSpoon;

    //for button Toileting
    @FindBy(xpath = "//*[@class='active-result result-selected']")
    WebElement selectToiletTrained;
    @FindBy(xpath = "//*[@class='active-result']")
    WebElement itemDressesAlone;
    @FindBy(xpath = "//*[@class='chosen-single']//span")
    WebElement itemToiletTrained;
    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectItemToiletingListButton;
    //for button  Treatment
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemSurgery;
    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectedSurgery;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemMedicalTrial;
    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectedMedicalTrial;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemRemission;
    @FindBy(xpath = "//*[@class='chosen-single']/span")
    WebElement selectedRemission;


    //fill Other field
    @FindBy(xpath = "//*[@class='col-sm-12']/input']")
    WebElement otherMilestoneFiled;

    //Years-Month button
    @FindBy(xpath = ".//*[@id='milestone_years']")
    WebElement yearsButton;
    @FindBy(xpath = ".//*[@id='milestone_months']")
    WebElement monthButton;

    //field select language
    @FindBy(xpath = "//*[@class='chosen-search']/input")
    WebElement selectLanguageField;


    //field  post

    @FindBy(xpath = "//*[@placeholder='Tell us more about this milestone']")
    WebElement postField;//*[@class='col-sm-12']/input
    @FindBy(xpath = "//*[@class='col-sm-12']/input")
    WebElement textField;


    //button submit
    @FindBy(xpath = ".//*[@id='submit']")
    WebElement submitButton;

    //alerts
    @FindBy(xpath = "//*[@class = 'alert alert-danger alert-dismissible ng-hide']/div")
    WebElement alertRequiredFields;
    @FindBy(xpath = "//*[@style='padding-left: 5px;']/span")
    WebElement numbersOnlyForMonths;
    @FindBy(xpath = "//*[@style='padding-right: 5px;']/span")
    WebElement numbersOnlyForYears;


    //constructor
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

    //set for language
    public MilestoneOnMainPage clickOnLanguageOption() {
        clickElement(languageButton);
        return this;
    }

    public MilestoneOnMainPage clickOnSelectItemOption() {
        clickElement(selectItemListButton);
        return this;
    }

    public MilestoneOnMainPage clickOnLanguageItemList() {
        clickElement(selectItemList);
        return this;
    }

    public MilestoneOnMainPage clickFirstItemFromLanguageItemList() {
        clickElement(itemSmiles);
        return this;
    }

    public MilestoneOnMainPage clickSecondItemFromLanguageList() {
        clickElement(itemBabbles);
        return this;
    }

    public MilestoneOnMainPage clickThirdItemFromLanguageList() {
        clickElement(itemFirstWord);
        return this;
    }

    public MilestoneOnMainPage clickFourthItemFromLanguageList() {
        clickElement(itemWords);
        return this;
    }

    //set for Movement
    public MilestoneOnMainPage clickOnMovementOption() {
        clickElement(movementButton);
        return this;
    }

    public MilestoneOnMainPage clickOnSelectMovementItemOption() {
        clickElement(selectItemMovementButton);
        return this;
    }

    public MilestoneOnMainPage clickOnMovementItemList() {
        clickElement(selectItemList);
        return this;
    }

    public MilestoneOnMainPage clickFirstItemFromMovementList() {
        clickElement(itemHoldsHead);
        return this;
    }

    public MilestoneOnMainPage clickSecondItemFromMovementList() {
        clickElement(itemReachesForObjects);
        return this;
    }

    public MilestoneOnMainPage clickRollsOverFromMovementList() {
        clickElement(itemRollsOver);
        return this;
    }

    public MilestoneOnMainPage clickFifthItemFromMovementList() {
        clickElement(itemCrawls);
        return this;
    }

    public MilestoneOnMainPage clickEighthItemFromMovementList() {
        clickElement(itemRuns);
        return this;
    }
    //for eating

    public MilestoneOnMainPage clickOnEatingOption() {
        clickElement(eatingButton);
        return this;
    }

    public MilestoneOnMainPage clickOnSelectEatingItemOption() {
        clickElement(selectEatingItemButton);
        return this;
    }


    public MilestoneOnMainPage clickOnEatingItemList() {
        clickElement(selectEatingItemList);
        return this;
    }

    public MilestoneOnMainPage clickFirstItemFromEatingItemList() {
        clickElement(itemHoldsBottles);
        return this;
    }

    public MilestoneOnMainPage clickEatsWithSpoonFromEatingList() {
        clickElement(itemEatsWithSpoon);
        return this;
    }

    //for Toileting
    public MilestoneOnMainPage clickOnToiletingOption() {
        clickElement(toiletingButton);
        return this;
    }

    public MilestoneOnMainPage clickOnSelectItemToiletingItemOption() {
        clickElement(selectItemToiletingListButton);
        return this;
    }

    public MilestoneOnMainPage clickFirstItemToiletingItemList() {
        clickElement(itemToiletTrained);
        return this;
    }

    public MilestoneOnMainPage clickSecondItemToiletingList() {
        clickElement(itemDressesAlone);
        return this;
    }

    //for treatment
    public MilestoneOnMainPage clickOnTreatmentOption() {
        clickElement(treatmentButton);
        return this;
    }

    public MilestoneOnMainPage clickOnSelectTreatmentItemOption() {
        clickElement(selectTreatmentButton);
        return this;
    }


    public MilestoneOnMainPage clickFirstItemFromTreatmentItemList() {
        clickElement(itemSurgery);
        return this;
    }

    public MilestoneOnMainPage clickSecondItemTreatmentList() {
        clickElement(itemMedicalTrial);
        return this;
    }

    public MilestoneOnMainPage clickThirdItemTreatmentList() {
        clickElement(itemRemission);
        return this;
    }

    //for other
    public MilestoneOnMainPage clickOnOtherOption() {
        clickElement(otherButton);
        return this;
    }

    public MilestoneOnMainPage clickOnSelectOtherItemOption() {
        clickElement(otherButton);
        return this;
    }


    //for year
    public MilestoneOnMainPage clickOnYearsOption(String year) {
        setElementText(yearsButton, year);
        return this;
    }

    public MilestoneOnMainPage chooseButtonYearsList(String year) {
        clickElement(yearsButton);
        return this;
    }

    public MilestoneOnMainPage clickOnMonthOption(String months) {
        setElementText(monthButton, months);
        return this;
    }

    public MilestoneOnMainPage clickOnLanguageItemOption(String text) {
        setElementText(selectLanguageField, text);
        return this;
    }


    public MilestoneOnMainPage chooseButtonMonthList(String month) {
        clickElement(monthButton);
        return this;

    }

    //fill text
    //post

    public MilestoneOnMainPage fillTextField(String post) {
        setElementText(postField, post);
        return this;
    }

    public MilestoneOnMainPage fillOtherField(String text) {
        setElementText(textField, text);
        return this;
    }

    public MilestoneOnMainPage sendPost() {
        clickElement(submitButton);
        return this;
    }


    //check alert presence

    public boolean alertMessageNotValidYear() {
        return exists(numbersOnlyForYears);
    }

    public boolean alertMessageNotValidMonth() {
        return exists(numbersOnlyForMonths);
    }

    public boolean alertMessageRequiredFields() {
        return exists(alertRequiredFields);
    }



}

