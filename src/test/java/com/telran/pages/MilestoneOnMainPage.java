package com.telran.pages;


import com.telran.LogLog4j;
import org.apache.log4j.Logger;
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
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    HashMap<String,WebElement>buttonsAndItemsMap = new HashMap<String, WebElement>();
    //Labels of categories
    @FindBy(xpath = "//div [@class='col-sm-8']/label")
    WebElement developmentalMilestoneTitle;
    @FindBy(xpath = "//div [@class='col-sm-4']/label")
    WebElement treatmentMilestoneTitle;
    //Category Developmental Milestone buttons
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Language')]")
    WebElement languageButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Movement')]")
    WebElement movementButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Eating')]")
    WebElement eatingButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Treatment')]")
    WebElement treatmentButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Toileting')]")
    WebElement toiletingButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Other')]")
    WebElement otherButton;
    //Dropdown list Button
    @FindBy(xpath = "//*[@class ='chosen-single']/div/b")
    WebElement selectDropDownListButton;
    // DropdownList elements of Language
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemSmiles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemBabbles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemFirstWord;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemTwoThreeWords;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemSpeaksInFullSentences;
    // DropdownList elements of Movement
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHoldsHead;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemReachesForObjects;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemRollsOver;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemSitsWithoutSupport;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemCrawls;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement itemPullsToStand;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemWalk;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemRuns;
    // DropdownList elements of Eating
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemHoldsBottles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemEatsWithSpoon;
    // DropdownList elements of Toileting
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemToiletTrained;//+
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemDressesAlone;//+
    //DropdownList elements of Treatment
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemSurgery;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemMedicalTrial;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemRemission;
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
    @FindBy(xpath = "//*[@class='col-sm-12']/input")
    WebElement textField;
    //button submit
    @FindBy(xpath = "//*[@id='submit']")
    WebElement submitButton;
    //alerts
    @FindBy(xpath = "//*[text()='REQUIRED FIELDS']")
    WebElement alertRequiredFields;
    //*[contains(text(), "Numbers")]
    @FindBy(xpath = ".//*[@id='milestone_years']/../span[contains(text(),'Numbers only')]")
   // @FindBy(xpath = ".//*[@id='milestone_years']/../span")
    WebElement numbersOnlyForMonths;
   // @FindBy(xpath = "//*[@id='milestone_years']/../span[contains(text(),'Numbers only')]")
    @FindBy(xpath = "//*[@placeholder='Years']/../span")
    WebElement numbersOnlyForYears;
    //elements in created post
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[1]/td[2]")
    WebElement ageOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[2]/td[2]")
    WebElement milestoneTypeOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[3]/td[2]")
    WebElement milestoneOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-note']")
    WebElement textNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-note']")
    WebElement textInNewCreatedPost;


    //constructor
    public MilestoneOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = baseUrl + "/home";
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
        Log.info("This is Milestone Panel");
        return exists(developmentalMilestoneTitle);
    }


    public MilestoneOnMainPage clickOnSelectItemOption() {              //button opening drop-down list
        Log.info(" Select item option");
        clickElement(selectDropDownListButton);
        return this;
    }

    //for other
    public MilestoneOnMainPage clickOnSelectOtherItemOption() {
        Log.info("Select other item option");
        clickElement(otherButton);
        return this;}

    //for year
    public MilestoneOnMainPage clickOnYearsOption(String year) {
        Log.info("Years option:" + year);
        setElementText(yearsButton, year);
        return this;}

    //for month
    public MilestoneOnMainPage clickOnMonthOption(String months) {
        Log.info("Month Option:" + months);
        setElementText(monthButton, months);
        return this;
    }

    public MilestoneOnMainPage clickOnLanguageItemOption(String text) {
        Log.info("Language Item Option:" + text);
        setElementText(selectLanguageField, text);
        return this;
    }

    //fill text post
    public MilestoneOnMainPage fillTextField(String post) {
        Log.info("Fill Text Field:" + post);
        setElementText(inputTextPostField, post);
        return this;}

    // fill text of category "Other"
    public MilestoneOnMainPage fillOtherField(String text) {
        Log.info("Fill Other Field:" + text);
        setElementText(textField, text);
        return this;}

    // Submit button
    public MilestoneOnMainPage sendPost() {
        Log.info("Send post");
        clickElement(submitButton);
        return this;}

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

    //checking data in created post
    private WebElement getWebElementByName(String name) {
        return buttonsAndItemsMap.get(name);
    }

    public MilestoneOnMainPage clickOnElement(String name){
        Log.info("Element Name:" + name);
        WebElement element=getWebElementByName(name);
        element.click();
        return this;
    }
    public void fillAllElementsAndItemsToMap() {
        // buttons with names from WebPage
        buttonsAndItemsMap.put(languageButton.getText(),languageButton);
        buttonsAndItemsMap.put(movementButton.getText(),movementButton);
        buttonsAndItemsMap.put(eatingButton.getText(),eatingButton);
        buttonsAndItemsMap.put(treatmentButton.getText(),treatmentButton);
        buttonsAndItemsMap.put(toiletingButton.getText(),toiletingButton);
        buttonsAndItemsMap.put(otherButton.getText(),otherButton);
        //items with names from category "Language"
        buttonsAndItemsMap.put("Smiles",itemSmiles);
        buttonsAndItemsMap.put("Babbles",itemBabbles);
        buttonsAndItemsMap.put("First word",itemFirstWord);
        buttonsAndItemsMap.put("2-3 words",itemTwoThreeWords);
        buttonsAndItemsMap.put("Speaks in full sentences",itemSpeaksInFullSentences);
        // items with names from category "Movement"
        buttonsAndItemsMap.put("Holds head",itemHoldsHead);
        buttonsAndItemsMap.put("Reaches for objects",itemReachesForObjects);
        buttonsAndItemsMap.put("Rolls over",itemRollsOver);
        buttonsAndItemsMap.put("Sits without support",itemSitsWithoutSupport);
        buttonsAndItemsMap.put("Crawls",itemCrawls);
        buttonsAndItemsMap.put("Pulls to stand",itemPullsToStand);
        buttonsAndItemsMap.put("Walk",itemWalk);
        buttonsAndItemsMap.put("Runs",itemRuns);
        // items with names from category "Eating"
        buttonsAndItemsMap.put("Holds bottle",itemHoldsBottles);
        buttonsAndItemsMap.put("Eats with spoon",itemEatsWithSpoon);
        // items with names from category "Toileting"
        buttonsAndItemsMap.put("Toilet trained",itemToiletTrained);
        buttonsAndItemsMap.put("Dresses alone",itemDressesAlone);
        // items with names from category "Treatment"
        buttonsAndItemsMap.put("Surgery",itemSurgery);
        buttonsAndItemsMap.put("Medical trial",itemMedicalTrial);
        buttonsAndItemsMap.put("Remission",itemRemission);
    }

    public boolean isMilestoneTrue(String name){return this.verifyTextBoolean(milestoneOnNewCreatedPost,name);}

    public boolean isTypeTrue(String name){return this.verifyTextBoolean(milestoneTypeOnNewCreatedPost,name);}

    public boolean isAgeIsCorrect(String age) {return verifyTextBoolean(ageOnNewCreatedPost, age);}

    public boolean isTextCorrect(String name) {return verifyTextBoolean(textNewCreatedPost, name);}


    public void waitForPostLoaded() {
        try {
            this.waitUntilElementIsLoaded(milestoneOnNewCreatedPost);
        } catch (IOException e) {
            System.out.println("no post loaded error:"+e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("no post loaded error:"+e.getMessage());
        }
    }


    public boolean isOtherTextCorrect(String name) {return verifyTextBoolean(milestoneOnNewCreatedPost, name);}
}