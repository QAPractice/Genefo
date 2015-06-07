package com.telran.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Zizi, Christina and Mariya on 5/28/2015.
 *
 */
public class MilestoneOnMainPage extends Page {

    HashMap<String,WebElement>buttonsAndItemsMap = new HashMap<String, WebElement>();
    //Labels of categories
    @FindBy(xpath = "//div [@class='col-sm-8']/label")
    WebElement developmentalMilestoneTitle;
    @FindBy(xpath = "//div [@class='col-sm-4']/label")
    WebElement treatmentMilestoneTitle;


    //Category Developmental Milestone buttons
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Language')]")         //+
    WebElement languageButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Movement')]")         //+
    WebElement movementButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Eating')]")          //+
    WebElement eatingButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Treatment')]")        //+
    WebElement treatmentButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Toileting')]")        //+
    WebElement toiletingButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Other')]")            //+
    WebElement otherButton;

    //Dropdown list Button
    @FindBy(xpath = "//*[@class ='chosen-single']/div/b")                                     //+
    WebElement selectDropDownListButton;


    /* @FindBy(xpath = "/*//*[contains(text(),'Select a Language Milestone')]")            //What is it field?
    WebElement selectItemList;*/
    /*@FindBy(xpath = "/*//*[contains(text(),'Select a Movement Milestone')]")           //What is it field?
    WebElement selectItemMovementList;    //?
    @FindBy(xpath = "/*//*[contains(text(),'Select a Movement Milestone')]/../div/b"  //This is  Web Element "selectDropDownListButton"
    WebElement selectItemMovementButton;  //?*/
    @FindBy(xpath = "//*[@class='chosen-single']/span")  //?
    WebElement selectEatingItemList;
    /* @FindBy(xpath = "/*//*[@class='chosen-single']/span/../div/b")     //This is  Web Element "selectDropDownListButton"
    WebElement selectEatingItemButton;*/
    @FindBy(xpath = "//*[@class='chosen-single']/span")    //?
    WebElement selectToiletingItemList;
    /*@FindBy(xpath = "/*//*[@class='chosen-single']//div/b")         //This is  Web Element "selectDropDownListButton"
    WebElement selectToiletingItemListButton;*/
    @FindBy(xpath = "//*[@class='ng-binding'][contains(text(),'Select a Treatment Milestone')]") //?
    WebElement selectTreatmentItemList;
    /*@FindBy(xpath = "/*//*[@class='chosen-single']//div/b")       //This is  Web Element "selectDropDownListButton"
    WebElement selectTreatmentButton;*/

    // DropdownList elements of Language
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")        //+
    WebElement itemSmiles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")        //+
    WebElement itemBabbles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")        //+
    WebElement itemFirstWord;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")        //+
    WebElement itemTwoThreeWords;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")        //+
    WebElement itemSpeaksInFullSentences;

    @FindBy(xpath = "//*[@class='chosen-single']/span")        //What is it field?
    WebElement selectedSmiles;
    @FindBy(xpath = "//*[@class='chosen-single']/span")        //What is it field?
    WebElement selectedBabbles;
    @FindBy(xpath = "//*[@class='chosen-single']/span")        //What is it field?
    WebElement selectedFirsWord;
    @FindBy(xpath = "//*[@class='chosen-results']/li[5]")      //What is it field?
    WebElement selectedTwoThreeWords;

    // DropdownList elements of Movement
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")        //+
    WebElement itemHoldsHead;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")        //+
    WebElement itemReachesForObjects;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")        //+
    WebElement itemRollsOver;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")        //+
    WebElement itemSitsWithoutSupport;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")        //+
    WebElement itemCrawls;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='6']")        //+
    WebElement itemPullsToStand;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='7']")        //+
    WebElement itemWalk;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='8']")        //+
    WebElement itemRuns;

    @FindBy(xpath = "//*[@class='chosen-single']/span")      //What is it field?
            WebElement selectedReachesForObjects;
    @FindBy(xpath = "//*[@class='chosen-single']/span")      //What is it field?
            WebElement selectedCraws;
    @FindBy(xpath = "//*[@class='chosen-single']/span")      //What is it field?
    WebElement selectedHoldsHead;
    @FindBy(xpath = "//*[@class='chosen-single']/span")      //What is it field?
            WebElement selectedRuns;


    // DropdownList elements of Eating
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")     //+
    WebElement itemHoldsBottles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")     //+
    WebElement itemEatsWithSpoon;


    @FindBy(xpath = "//*[@class='chosen-single']/span")     //What is it field?
            WebElement selectHoldsBottles;
    @FindBy(xpath = " //*[@class='chosen-single']/span")     //What is it field?
            WebElement selectEatsWithSpoon;

    // DropdownList elements of Toileting
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")    //+
    WebElement itemToiletTrained;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")    //+
    WebElement itemDressesAlone;

    @FindBy(xpath = "//*[@class='chosen-single']/span")           //What is it field?
    WebElement selectItemToiletingListButton;
    @FindBy(xpath = "//*[@class='active-result result-selected']")   //What is it field?
    WebElement selectToiletTrained;


    //DropdownList elements of Treatment
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")    //+
    WebElement itemSurgery;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")    //+
    WebElement itemMedicalTrial;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")    //+
    WebElement itemRemission;


    @FindBy(xpath = "//*[@class='chosen-single']/span")        //What is it field?
    WebElement selectedRemission;
    @FindBy(xpath = "//*[@class='chosen-single']/span")          //What is it field?
    WebElement selectedSurgery;
    @FindBy(xpath = "//*[@class='chosen-single']/span")          //What is it field?
    WebElement selectedMedicalTrial;


    //fill Other field
    // @FindBy(xpath = "//*[@class='col-sm-12']/input']")
    WebElement otherMilestoneFiled;
    //Field for Specify Milestone
    @FindBy(xpath = "//*[@placeholder='Please Specify Your Milestone']")
    WebElement fillOtherItemList;

    //Years-Month button
    @FindBy(xpath = ".//*[@id='milestone_years']")
    WebElement yearsButton;
    @FindBy(xpath = ".//*[@id='milestone_months']")
    WebElement monthButton;

    //field input text for language
    @FindBy(xpath = "//*[@class='chosen-search']/input")
    WebElement selectLanguageField;


    //Field for input PostText
    @FindBy(xpath = "//*[@class='form-group']/textarea")
    WebElement inputTextPostField;

    @FindBy(xpath = "//*[@class='col-sm-12']/input")    // //What is it field?
    WebElement textField;


    //button submit
    @FindBy(xpath = "//*[@id='submit']")
    WebElement submitButton;



    //alerts
    @FindBy(xpath = "//*[@class = 'alert alert-danger alert-dismissible ng-hide']/div")
    WebElement alertRequiredFields;
    @FindBy(xpath = "//*[@class='col-sm-2'][@style='padding-left: 5px;']/span")
    WebElement numbersOnlyForMonths;
    @FindBy(xpath = "//*[@style='padding-right: 5px;']/span")
    WebElement numbersOnlyForYears;

    //elements in created post
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[1]/td[2]")
    WebElement ageOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[2]/td[2]")
    WebElement milestoneTypeOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[3]/td[2]")
    WebElement milestoneOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-note ng-binding']")
    WebElement textInNewCreatedPost;



    //constructor
    public MilestoneOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://genefo.com/home";
    }

    // Waits until title of our 'Milestone' Panel appears on the screen
    public void waitUntilMilestonePanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(developmentalMilestoneTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Checks that title of our 'Milestone' Panel have appeared on the screen so we can work with it.
    public boolean isOnMilestonePanel() {
        return exists(developmentalMilestoneTitle);
    }

    //set for language
    public MilestoneOnMainPage clickOnLanguageOption() {
        clickElement(languageButton);
        return this;
    }

    public MilestoneOnMainPage clickOnSelectItemOption() {     //кнопка, открыващая дроп даун лист
        clickElement(selectDropDownListButton);
        return this;
    }

    public MilestoneOnMainPage clickOnLanguageItemList() {
        clickElement(selectDropDownListButton);
        return this;
    }

    public MilestoneOnMainPage clickSmilesFromLanguageItemList() {
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

    public MilestoneOnMainPage clickTwoThreeWordsFromLanguageList() {
        clickElement(itemTwoThreeWords);
        return this;
    }



    //set for Movement
    public MilestoneOnMainPage clickOnMovementOption() {
        clickElement(movementButton);
        return this;
    }

    public MilestoneOnMainPage clickOnSelectMovementItemOption() {
        clickElement(selectDropDownListButton);
        return this;
    }

    public MilestoneOnMainPage clickOnMovementItemList() {
        clickElement(selectDropDownListButton);
        return this;
    }

    public MilestoneOnMainPage clickHoldHeadFromMovementList() {
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

    /*public MilestoneOnMainPage clickOnSelectEatingItemOption() {      // можно удалить
        clickElement(selectDropDownListButton);
        return this;
    }*/


    public MilestoneOnMainPage clickOnEatingItemList() {
        clickElement(selectEatingItemList);
        return this;
    }

    public MilestoneOnMainPage clickHoldsBottleFromEatingItemList() {
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

    public MilestoneOnMainPage clickToiletTrainedFromToiletingItemList() {
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

  /*  public MilestoneOnMainPage clickOnSelectTreatmentItemOption() {    //можно удалить
        clickElement(selectTreatmentButton);
        return this;
    }
*/

    public MilestoneOnMainPage clickTreatmentFromTreatmentItemList() {
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

    //fill text post
    public MilestoneOnMainPage fillTextField(String post) {
        setElementText(inputTextPostField, post);
        return this;
    }
    // fill text of category "Other"
    public MilestoneOnMainPage fillOtherField(String text) {
        setElementText(textField, text);
        return this;
    }
    // Submit button
    public MilestoneOnMainPage sendPost() {
        clickElement(submitButton);
        return this;
    }


    //check alert presence
    public boolean alertMessageNotValidYear() {return exists(numbersOnlyForYears);}

    public boolean alertMessageNotValidMonth() {
        return exists(numbersOnlyForMonths);
    }

    public boolean alertMessageRequiredFields() {
        return exists(alertRequiredFields);
    }

    //checking data in created post
    private WebElement getWebElementByName(String name){
        return buttonsAndItemsMap.get(name);
    }

    public MilestoneOnMainPage clickOnElement(String name){
        WebElement element=getWebElementByName(name);
        element.click();
        return this;
    }

    public void fillAllElementsAndItemsToMap(){
        // buttons with names from WebPage
        buttonsAndItemsMap.put(languageButton.getText(),languageButton);
        buttonsAndItemsMap.put(movementButton.getText(),movementButton);
        buttonsAndItemsMap.put(eatingButton.getText(),eatingButton);
        buttonsAndItemsMap.put(treatmentButton.getText(),treatmentButton);
        buttonsAndItemsMap.put(toiletingButton.getText(),toiletingButton);
        buttonsAndItemsMap.put(otherButton.getText(),otherButton);
        // items with names from category "Language"
        buttonsAndItemsMap.put(itemSmiles.getText(),itemSmiles);
        buttonsAndItemsMap.put(itemBabbles.getText(),itemBabbles);
        buttonsAndItemsMap.put(itemFirstWord.getText(),itemFirstWord);
        buttonsAndItemsMap.put(itemTwoThreeWords.getText(),itemTwoThreeWords);
        buttonsAndItemsMap.put(itemSpeaksInFullSentences.getText(),itemSpeaksInFullSentences);
        // items with names from category "Movement"
        buttonsAndItemsMap.put(itemHoldsHead.getText(),itemHoldsHead);
        buttonsAndItemsMap.put(itemReachesForObjects.getText(),itemReachesForObjects);
        buttonsAndItemsMap.put(itemRollsOver.getText(),itemRollsOver);
        buttonsAndItemsMap.put(itemSitsWithoutSupport.getText(),itemSitsWithoutSupport);
        buttonsAndItemsMap.put(itemCrawls.getText(),itemCrawls);
        buttonsAndItemsMap.put(itemPullsToStand.getText(),itemPullsToStand);
        buttonsAndItemsMap.put(itemWalk.getText(),itemWalk);
        buttonsAndItemsMap.put(itemWalk.getText(),itemRuns);
        // items with names from category "Eating"
        buttonsAndItemsMap.put(itemHoldsBottles.getText(),itemHoldsBottles);
        buttonsAndItemsMap.put(itemEatsWithSpoon.getText(),itemEatsWithSpoon);
        // items with names from category "Toileting"
        buttonsAndItemsMap.put(itemToiletTrained.getText(),itemToiletTrained);
        buttonsAndItemsMap.put(itemDressesAlone.getText(),itemDressesAlone);
        // items with names from category "Treatment"
        buttonsAndItemsMap.put(itemSurgery.getText(),itemSurgery);
        buttonsAndItemsMap.put(itemMedicalTrial.getText(),itemMedicalTrial);
        buttonsAndItemsMap.put(itemRemission.getText(),itemRemission);
    }


    public boolean isMilestoneTrue(String name){return this.verifyTextBoolean(milestoneOnNewCreatedPost,name);}

    public boolean isTypeTrue(String name){return this.verifyTextBoolean(milestoneTypeOnNewCreatedPost,name);}

    public boolean isAgeIsCorrect(String age) {return verifyTextBoolean(ageOnNewCreatedPost, age);}

    public boolean isTextCorrect(String name) {return verifyTextBoolean(textInNewCreatedPost, name);

    }


    public void waitForPostLoaded() {
        try {
            this.waitUntilElementIsLoaded(milestoneOnNewCreatedPost);
        } catch (IOException e) {
            System.out.println("no post loaded error:"+e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("no post loaded error:"+e.getMessage());
        }
    }


    public boolean isOtherTextCorrect(String name) {
        return verifyTextBoolean(textInNewCreatedPost, name);

    }



}

