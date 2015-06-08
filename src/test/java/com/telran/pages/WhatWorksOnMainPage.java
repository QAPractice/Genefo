package com.telran.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Iakov Volf 27.05.15.
 */
public class WhatWorksOnMainPage extends Page {


    //Category Symptom buttons
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Therapy')]")
    WebElement therapyButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Equipment')]")
    WebElement equipmentButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Nutrition')]")
    WebElement nutritionButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Exercises')]")
    WebElement exercisesButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Alternative')]")
    WebElement alternativeButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[6][contains(text(),'Other')]")
    WebElement otherButton;

    //Dropdown list
    @FindBy(xpath = "//*[@placeholder = 'Please Specify Your Item']")
    WebElement specifyItemForOtherOption;

    @FindBy(xpath = "//*[contains(text(),'Please select a specific item')]")
    WebElement selectItemList;

    @FindBy(xpath = "//*[contains(text(),'Please select a specific item')]/../div/b")
    WebElement selectItemListButton;

    // Here we distinguish list that chosen, from list that is not chosen
    @FindBy(xpath = "//div[contains(@class,'chosen-with-drop chosen-container-active')]//span[contains(text(),'Please select a specific item')]")
    WebElement ItemListButtonThatChosen;


    //elements of dropdown list
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement firstItemInList;

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement secondItemInList;

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement thirdItemInList;

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement fourthItemInList;

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement fifthItemInList;

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement sixthItemInList;

    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement seventhItemInList;


    // Rating star( marked one. Have asterisk sign in definition)
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/i[3]/span[contains(text(),'*')]")
    WebElement thirdMarkedRatingStar;

    // Rating star( non-marked one. Do not have asterisk sign in definition)
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/i[3]/span[not(contains(text(),'*'))]")
    WebElement thirdNonMarkedRatingStar;

    // Rating star - marked and non-marked together
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[3]")
    WebElement thirdRatingStar;

    // Serves as indication that we are on 'WhatWorks' Panel.
    @FindBy(xpath = "//label[@for='what_works_category_1']/../label[@for='symptoms_select'] ")
    WebElement categorySymptomTitle;

    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-pristine ng-valid'][@ng-model='what_works_rating']//span[@class='sr-only ng-binding']")
    WebElement allStarsTogether;


    // text field for posting
    @FindBy(xpath = "//textarea[@name = 'bio']")
    WebElement postField;

    @FindBy(xpath = "//button[@id='submit'][contains(text(),'Post')]")
    WebElement submitButton;


    // Upper Tab of sent posts
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]")
    WebElement  UpperSentPostTab;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//div[@class='post-note ng-binding']")
    WebElement  SentPostText;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[1]/td[2]")
    WebElement  SentPostCategory;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"what_works_rating\"]/i[3]/span[contains(text(),'*')]")
    WebElement  filledThirdStarInSentPost;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"what_works_rating\"]/i[4]/span[not(contains(text(),'*'))]")
    WebElement nonFilledFourthStarInSentPost;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[2]/td[2]")
    WebElement ListItemInSentPost;

    private String textInListItem; // Serves to keep text of the item from the list to give it after for assertion.

    // Data structure that keeps button( type WebElement )and what is written on it (String)
    // row by row. Has two methods - put() and get() (see below)
    private HashMap<String,WebElement>optionsLocator = new HashMap<String,WebElement>();

    // Sort of array but without size limits. Keeps only variables of  WebElement type.
    // has two methods - add() and put()  (see below)
    private ArrayList<WebElement> itemsInListById = new ArrayList<WebElement>();


    public WhatWorksOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        //this.PAGE_URL = "http://genefo.com/home";
    }

    // Waits until title of our 'What works' Panel appears on the screen
    public void waitUntilWhatWorksPanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(categorySymptomTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Checks that title of our 'What works' Panel have appeared on the screen so we can work with it.
    public boolean isOnWhatWorksPanel() {
        waitUntilWhatWorksPanelIsLoaded();
        return exists(categorySymptomTitle);
    }
    // Fills data structure optionsLocator (has type HashMap<String,WebElement>)
    // and data structure itemsInListById ( has type ArrayList<WebElement> )
    public void defineOptionsLocatorAndItemList(){
        optionsLocator.put("Therapy",therapyButton);
        optionsLocator.put("Equipment",equipmentButton);
        optionsLocator.put("Nutrition",nutritionButton);
        optionsLocator.put("Exercises",exercisesButton);
        //optionsLocator.put("",alternativeButton);
        optionsLocator.put("Alternative",alternativeButton);
        optionsLocator.put("Other",otherButton);
        itemsInListById.add(null);
        itemsInListById.add(firstItemInList);
        itemsInListById.add(secondItemInList);
        itemsInListById.add(thirdItemInList);
        itemsInListById.add(fourthItemInList);
        itemsInListById.add(fifthItemInList);
        itemsInListById.add(sixthItemInList);
        itemsInListById.add(seventhItemInList);
    }

    public WhatWorksOnMainPage clickOnOption(String option) {
        try{
            clickElement(optionsLocator.get(option));// Choose and click on button that has 'option' string written on it
        }
        catch (Exception e){  e.printStackTrace();  // In this way we define our own exception
            System.out.println("Wrong option! \nOption with name :"+option+" does not exist!");
        }
        return this;
    }


    public WhatWorksOnMainPage clickOnItemList() {
        clickElement(selectItemList);
        return this;
    }

    public WhatWorksOnMainPage chooseItemFromItemList( int itemNumber ) {
        WebElement optionChooser;
        try {
            optionChooser=itemsInListById.get(itemNumber); // choose item that corresponds to number 'itemNumber'
            textInListItem = optionChooser.getText();
            clickElement(optionChooser);
        }
        catch (Exception e){ e.printStackTrace();           // In this way we define our own exception
            System.out.println("Wrong item number! \nItem with number :"+itemNumber+" does not exist!");
        }
        return this;
    }

    public WhatWorksOnMainPage fillTextField(String post) {
        setElementText(postField, post);
        return this;
    }


    public WhatWorksOnMainPage fillItemForOtherOption(String item) {
        setElementText(specifyItemForOtherOption, item);
        return this;
    }

    public WhatWorksOnMainPage sendPost() {
        clickElement(submitButton);
        return this;
    }

    // We need to click on all stars together to set free each one of them
    public WhatWorksOnMainPage clickOnAllStarsTogether() {
        clickElement(allStarsTogether);
        return this;
    }

    // Click on the third star
    public WhatWorksOnMainPage rateItThree() {
        clickElement(thirdRatingStar);
        return this;
    }

    // Methods for verifying items on sent upper post for What Works Tab

    public Boolean verifyTextFromSentPost(String text)  {
        return verifyTextBoolean(SentPostText, text);
    }


    public Boolean verifyCategoryExistsInSentPost(String category)  {

        return verifyTextBoolean(SentPostCategory, category);
    }


    // Use it after 'chooseFirstItemFromItemList()' method: First you choose item, put it
    // in variable firstItemInListText ,
    // then you verify that it is seen on Sent Post Panel(on ListItemInSentPost WebElement).
    public Boolean verifyListItemCorrectInSentPost()  {
        return verifyTextBoolean(ListItemInSentPost, textInListItem );
    }

    public Boolean verifyOtherItemCorrectInSentPost(String item)  {
        return verifyTextBoolean(ListItemInSentPost, item );
    }


    public Boolean verifyThirdStarCheckedInSentPost()  {
        return exists(filledThirdStarInSentPost);
    }

    public Boolean verifyFourthStarNonCheckedInSentPost()  {
        return exists(nonFilledFourthStarInSentPost);
    }

    public Boolean verifyItemListIsChosen()  {
        return exists(ItemListButtonThatChosen);
    }



}











