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
    @FindBy(xpath = "//label[contains(text(),'Developmental Milestone')]")
    //WebElement developmentalYourProgressTitle;
    WebElement developmentalMilestoneTitle;

    /*@FindBy(xpath = "//div [@class='col-sm-8']/label")
    WebElement developmentalMilestoneTitle;
    @FindBy(xpath = "//div [@class='col-sm-4']/label")
    WebElement treatmentMilestoneTitle;*/
    @FindBy(xpath = "//*[@class='ng-scope active'][@popover='Report your progress']/span")
    // WebElement treatmentReportYourProgressTitle;
            WebElement treatmentMilestoneTitle;
    //Category Developmental Progress buttons
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Language')]")
    WebElement languageButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Movement')]")
    WebElement movementButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Daily living')]")
    WebElement dailyLivingButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Procedure')]")
    WebElement procedureButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Complications')]")
    WebElement complicationButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[@class='btn btn-default milestone_category_other ng-binding ng-scope'][contains(text(),'Other')]")
    WebElement otherButton;

    // DropdownList elements of Language
    //Dropdown list Button
   @FindBy(xpath = "//*[@class='col-sm-12']/select")
    WebElement selectDropDownListButton;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Speaks in full sentences']")
    WebElement itemSpeaksInFullSentences;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Babbles']")
    WebElement itemBabbles;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='First word']")
    WebElement itemFirstWord;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='2-3 words']")
    WebElement itemTwoThreeWords;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Smiles']")
    WebElement itemSmiles;
    // DropdownList elements of Movement
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Crawls']")
    WebElement itemCrawls;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Holds head']")
    WebElement itemHoldsHead;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Pulls to stand']")
    WebElement itemPullsToStand;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Reaches for objects']")
    WebElement itemReachesForObjects;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Rolls over']")
    WebElement itemRollsOver;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Runs']")
    WebElement itemRuns;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Sits without support']")
    WebElement itemSitsWithoutSupport;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Walk']")
    WebElement itemWalk;
    // DropdownList elements of Daily living
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Dresses alone']")
    WebElement itemDressesAlone;//+
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Eats with spoon']")
    WebElement itemEatsWithSpoon;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Holds bottle']")
    WebElement itemHoldsBottles;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Toilet trained']")
    WebElement itemToiletTrained;//+
    //DropdownList elements of Procedure
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Achilles tendon lengthening']")
    WebElement itemAchillesTendonLengthening;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Brain MRI']")
    WebElement itemBrainMRI;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Gastrostomy']")
    WebElement itemGastrostomy;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Medical trial']")
    WebElement itemMedicalTrial;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Remission']")
    WebElement itemRemission;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Spinal fusion surgery for scoliosis']")
    WebElement itemSpinalFusionSurgeryForScoliosis;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Strabismus surgery']")
    WebElement itemStrabismusSurgery;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Surgery']")
    WebElement itemSurgery;
    //DropdownList elements of Complication
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Behavioral problems']")
    WebElement itemBehavioralProblems;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Constipation']")
    WebElement itemConstipation;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Developmental delay']")
    WebElement itemDevelopmentalDelay;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Failure to thrive']")
    WebElement itemFailureToThrive;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Feeding difficulties']")
    WebElement itemFeedingDifficulties;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Gastroespophageal reflux']")
    WebElement itemGastroespophagealReflux;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Hearing loss']")
    WebElement itemHearingLoss;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Intellectual disability']")
    WebElement itemIntellectualDisability;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Obesity']")
    WebElement itemObesity;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Seizures']")
    WebElement itemSeizures;
    @FindBy(xpath = "//*[@class='ng-valid ng-touched ng-dirty ng-valid-parse']//option[@label='Sleep disorders']")
    WebElement itemSleepDisorders;
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
    @FindBy(xpath = ".//*[@id='milestone_years']/../span[contains(text(),'Must be a number between 0 and 200')]")
   // @FindBy(xpath = ".//*[@id='milestone_years']/../span")
    WebElement yearsNumber;
   // @FindBy(xpath = "//*[@id='milestone_years']/../span[contains(text(),'Numbers only')]")
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
        PageFactory.initElements(driver, this);
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
        Log.info(" Select item option");
        clickElement(selectDropDownListButton);
        return this;
    }

   /* //for other
    public MilestoneOnMainPage clickOnSelectOtherItemOption() {
        Log.info("Select other item option");
        clickElement(otherButton);
        return this;}
*/
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