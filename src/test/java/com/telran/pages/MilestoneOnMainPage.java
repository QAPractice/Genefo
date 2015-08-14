package com.telran.pages;


import com.telran.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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
    @FindBy(xpath = "//label[contains(text(),'Developmental Milestone')]")
//    @CacheLookup
    WebElement developmentalMilestoneTitle;

    //Category Developmental Progress buttons
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Language')]")
 //   @CacheLookup
    WebElement languageButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Movement')]")
 //   @CacheLookup
    WebElement movementButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Daily living')]")
//    @CacheLookup
    WebElement dailyLivingButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Procedure')]")
//    @CacheLookup
    WebElement procedureButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Complications')]")
//    @CacheLookup
    WebElement complicationButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[@class='btn btn-default milestone_category_other ng-binding ng-scope'][contains(text(),'Other')]")
 //   @CacheLookup
    WebElement otherButton;

    //Dropdown list Button
    @FindBy(xpath = "//*[@class='chosen-single']/div/b")
    WebElement selectDropDownListButton;
    // DropdownList elements of Language
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemSpeaksInFullSentences;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemBabbles;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemFirstWord;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemTwoThreeWords;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemSmiles;
    // DropdownList elements of Movement
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemCrawls;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemHoldsHead;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemPullsToStand;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemReachesForObjects;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemRollsOver;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement itemRuns;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemSitsWithoutSupport;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemWalk;
    // DropdownList elements of Daily living
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemDressesAlone;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemEatsWithSpoon;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemHoldsBottles;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemToiletTrained;
    //DropdownList elements of Procedure
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemAchillesTendonLengthening;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemBrainMRI;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemGastrostomy;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemMedicalTrial;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemRemission;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement itemSpinalFusionSurgeryForScoliosis;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemStrabismusSurgery;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemSurgery;
    //DropdownList elements of Complication
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemBehavioralProblems;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemConstipation;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemDevelopmentalDelay;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemFailureToThrive;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemFeedingDifficulties;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement itemGastroespophagealReflux;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemHearingLoss;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemIntellectualDisability;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='9']")
    WebElement itemObesity;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='10']")
    WebElement itemSeizures;
    @FindBy(xpath = "//*[@class='chosen-results']/li[@data-option-array-index='11']")
    WebElement itemSleepDisorders;
    //Years-Month button
    @FindBy(xpath = ".//*[@id='milestone_years']")
  //  @CacheLookup
    WebElement yearsButton;
    @FindBy(xpath = ".//*[@id='milestone_months']")
//    @CacheLookup
    WebElement monthButton;
    //field input text for language
    @FindBy(xpath = "//*[@class='chosen-search']/input")
    WebElement selectLanguageField;
    //Field for input PostText
    @FindBy(xpath = "//*[@class='form-group']/textarea")
//    @CacheLookup
    WebElement inputTextPostField;
    @FindBy(xpath = "//*[@class='col-sm-12']/input")
 //   @CacheLookup
    WebElement textField;
    //button submit
    @FindBy(xpath = "//*[@id='submit']")
  //  @CacheLookup
    WebElement submitButton;
    //alerts
    @FindBy(xpath = "//*[text()='REQUIRED FIELDS']")
    WebElement alertRequiredFields;
    //*[contains(text(), "Numbers")]
    @FindBy(xpath = ".//*[@id='milestone_years']/../span[contains(text(),'Must be a number between 0 and 200')]")
    WebElement yearsNumber;
   @FindBy(xpath = "//*[@class='col-sm-2']/span[contains(text(),'Must be number between 0 and 48')]")
    WebElement monthsNumber;
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
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
        this.PAGE_URL = "http://52.10.6.51:8080/home";
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
        Log.info(" Selecting item option");
        clickElement(selectDropDownListButton);
        return this;
    }

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
        return exists(yearsNumber);
    }

    public boolean alertMessageNotValidMonth() {
        return exists(monthsNumber);
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
        buttonsAndItemsMap.put(dailyLivingButton.getText(),dailyLivingButton);
        buttonsAndItemsMap.put(procedureButton.getText(),procedureButton);
        buttonsAndItemsMap.put(complicationButton.getText(),complicationButton);
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
        // items with names from category "Daily living"
        buttonsAndItemsMap.put("Dresses alone",itemDressesAlone);
        buttonsAndItemsMap.put("Eats with spoon",itemEatsWithSpoon);
        buttonsAndItemsMap.put("Holds bottle",itemHoldsBottles);
        buttonsAndItemsMap.put("Toilet trained",itemToiletTrained);
        // items with names from category "Procedure"
        buttonsAndItemsMap.put("Achilles tendon lengthening",itemAchillesTendonLengthening);
        buttonsAndItemsMap.put("Brain MRI",itemBrainMRI);
        buttonsAndItemsMap.put("Gastrostomy",itemGastrostomy);
        buttonsAndItemsMap.put("Medical trial",itemMedicalTrial);
        buttonsAndItemsMap.put("Remission",itemRemission);
        buttonsAndItemsMap.put("Spinal fusion surgery for scoliosis",itemSpinalFusionSurgeryForScoliosis);
        buttonsAndItemsMap.put("Strabismus surgery",itemStrabismusSurgery);
        buttonsAndItemsMap.put("Surgery",itemSurgery);
       //items with names from category " Complication"
        buttonsAndItemsMap.put("Behavioral problems",itemBehavioralProblems);
        buttonsAndItemsMap.put("Constipation",itemConstipation);
        buttonsAndItemsMap.put("Developmental delay",itemDevelopmentalDelay);
        buttonsAndItemsMap.put("Failure to thrive",itemFailureToThrive);
        buttonsAndItemsMap.put("Feeding difficulties",itemFeedingDifficulties);
        buttonsAndItemsMap.put("Gastroespophageal reflux",itemGastroespophagealReflux);
        buttonsAndItemsMap.put("Hearing loss",itemHearingLoss);
        buttonsAndItemsMap.put("Intellectual disability",itemIntellectualDisability);
        buttonsAndItemsMap.put("Obesity",itemObesity);
        buttonsAndItemsMap.put("Seizures",itemSeizures);
        buttonsAndItemsMap.put("Sleep disorders",itemSleepDisorders);

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